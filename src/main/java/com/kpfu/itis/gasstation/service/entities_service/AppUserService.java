package com.kpfu.itis.gasstation.service.entities_service;

import com.kpfu.itis.gasstation.entities.AppRole;
import com.kpfu.itis.gasstation.entities.AppUser;
import com.kpfu.itis.gasstation.forms.RegistrationForm;
import com.kpfu.itis.gasstation.forms.UserForm;
import com.kpfu.itis.gasstation.repositories.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.List;

/**
 * Created by Rustem.
 */
@Service
public class AppUserService {
    private AppUserRepository appUserRepository;
    private final AppRoleService appRoleService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public AppUserService(AppUserRepository appUserRepository, BCryptPasswordEncoder bCryptPasswordEncoder, AppRoleService appRoleService) {
        this.appUserRepository = appUserRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.appRoleService = appRoleService;
    }

    public void saveAppUserFromRegistrationForm(RegistrationForm registrationForm) {
        AppRole appRole = appRoleService.getClientAppRole();

        AppUser appUser = new AppUser();
        appUser.setLogin(registrationForm.getLogin());
        appUser.setEmail(registrationForm.getEmail());
        appUser.setName(registrationForm.getName());
        appUser.setHashedPassword(bCryptPasswordEncoder.encode(registrationForm.getPassword()));
        appUser.setAppRole(appRole);

        appUserRepository.save(appUser);
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

    public void deleteAppUserWithId(int id) {
        appUserRepository.deleteAppUserById(id);
    }

    public void updateAppUserFromUserFormById(int id, UserForm userForm) {
        AppUser appUser = appUserRepository.findById(id);

        appUser.setName(userForm.getName());
        appUser.setLogin(userForm.getLogin());
        appUser.setEmail(userForm.getEmail());
        appUser.setAppRole(appRoleService.getAppRoleWithName(userForm.getRole()));

        appUserRepository.save(appUser);
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
}
