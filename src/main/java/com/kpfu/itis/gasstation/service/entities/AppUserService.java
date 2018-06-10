package com.kpfu.itis.gasstation.service.entities;

import com.kpfu.itis.gasstation.entities.AppRole;
import com.kpfu.itis.gasstation.entities.AppUser;
import com.kpfu.itis.gasstation.forms.RegistrationForm;
import com.kpfu.itis.gasstation.forms.UserForm;
import com.kpfu.itis.gasstation.repositories.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.UserProfile;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.UUID;

/**
 * Created by Rustem.
 */
@Service
public class AppUserService {
    private final AppRoleService appRoleService;
    private final EntityManager entityManager;
    private AppUserRepository appUserRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public AppUserService(AppUserRepository appUserRepository, BCryptPasswordEncoder bCryptPasswordEncoder, AppRoleService appRoleService, EntityManager entityManager) {
        this.appUserRepository = appUserRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.appRoleService = appRoleService;
        this.entityManager = entityManager;
    }

    public AppUser saveAppUserFromRegistrationForm(RegistrationForm registrationForm) {
        AppRole appRole = appRoleService.getClientAppRole();

        AppUser appUser = new AppUser();
        appUser.setLogin(registrationForm.getLogin());
        appUser.setEmail(registrationForm.getEmail());
        appUser.setName(registrationForm.getName());
        appUser.setHashedPassword(bCryptPasswordEncoder.encode(registrationForm.getPassword()));
        appUser.setAppRole(appRole);

        return appUserRepository.save(appUser);
    }

    public UserForm createUserFormFromAppUserWithId(int id) {
        AppUser appUser = appUserRepository.findById(id);

        UserForm userForm = new UserForm();
        userForm.setName(appUser.getName());
        userForm.setLogin(appUser.getLogin());
        userForm.setEmail(appUser.getEmail());
        userForm.setRole(appUser.getAppRole().getName());

        return userForm;
    }

    public void addAppUserToModel(ModelMap model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof AppUser) {
            model.put("user", principal);
        }
    }

    public AppUser getCurrentAppUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof AppUser) {
            return (AppUser) principal;
        } else {
            return null;
        }
    }

    public AppUser getFirstManagerAppUser() {
        return appUserRepository.findFirstByAppRoleName(AppRoleService.ROLE_MANAGER);
    }

    public AppUser getAppUserByLogin(String login) {
        return appUserRepository.findByLogin(login);
    }

    public AppUser getAppUserByLoginAndPassword(String login, String password) {
        AppUser appUser = appUserRepository.findByLogin(login);
        if (appUser != null && bCryptPasswordEncoder.matches(password, appUser.getHashedPassword())) {
            return appUser;
        } else {
            return null;
        }
    }

    public AppUser getAppUserByEmail(String email) {
        return appUserRepository.findByEmail(email);
    }

    public List<AppUser> getAllAppUsers() {
        return appUserRepository.findAll();
    }

    public List<AppUser> getAllAppUsersWithoutCurrentAppUser() {
        List<AppUser> userlist = getAllAppUsers();
        return removeCurrentAppUserFromAppUsersList(userlist);
    }

    public List<AppUser> getAllAppUsersWithoutCurrentAppUserByLoginContains(String value) {
        List<AppUser> userlist = appUserRepository.findAllByLoginContains(value);
        return removeCurrentAppUserFromAppUsersList(userlist);
    }

    public int deleteAppUserWithId(int id) {
        return appUserRepository.deleteAppUserById(id);
    }

    public AppUser updateAppUserFromUserFormById(int id, UserForm userForm) {
        AppUser appUser = appUserRepository.findById(id);

        appUser.setName(userForm.getName());
        appUser.setLogin(userForm.getLogin());
        appUser.setEmail(userForm.getEmail());
        appUser.setAppRole(appRoleService.getAppRoleWithName(userForm.getRole()));

        return appUserRepository.save(appUser);
    }

    private List<AppUser> removeCurrentAppUserFromAppUsersList(List<AppUser> userlist) {
        AppUser currentUser = getCurrentAppUser();
        for (int i = 0; i < userlist.size(); i++) {
            AppUser user = userlist.get(i);
            if (user.getId() == currentUser.getId()) {
                userlist.remove(i);
                break;
            }
        }
        return userlist;
    }

    private String findAvailableUserName(String userName_prefix) {
        AppUser account = getAppUserByLogin(userName_prefix);
        if (account == null) {
            return userName_prefix;
        }
        int i = 0;
        while (true) {
            String userName = userName_prefix + "_" + i++;
            account = getAppUserByLogin(userName);
            if (account == null) {
                return userName;
            }
        }
    }

    public AppUser createAppUser(Connection<?> connection) {
        UserProfile userProfile = connection.fetchUserProfile();
        String email = userProfile.getEmail();
        AppUser appUser = getAppUserByEmail(email);
        if (appUser != null) {
            return appUser;
        }
        String userName_prefix = userProfile.getFirstName().trim().toLowerCase()
                + "_" + userProfile.getLastName().trim().toLowerCase();
        String userName = this.findAvailableUserName(userName_prefix);
        String randomPassword = UUID.randomUUID().toString().substring(0, 5);
        String encrytedPassword = bCryptPasswordEncoder.encode(randomPassword);

        appUser = new AppUser();
        appUser.setHashedPassword(encrytedPassword);
        appUser.setLogin(userName);
        appUser.setEmail(email);
        appUser.setName(userProfile.getFirstName() + " " + userProfile.getLastName());

        entityManager.persist(appUser);

        AppRole appRole = appRoleService.getClientAppRole();
        appUser.setAppRole(appRole);

        return appUser;
    }
}
