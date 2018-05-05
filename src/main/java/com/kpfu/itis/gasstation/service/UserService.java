package com.kpfu.itis.gasstation.service;

import com.kpfu.itis.gasstation.entities.AppRole;
import com.kpfu.itis.gasstation.entities.AppUser;
import com.kpfu.itis.gasstation.forms.RegistrationForm;
import com.kpfu.itis.gasstation.repositories.AppRoleRepository;
import com.kpfu.itis.gasstation.repositories.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

/**
 * Created by Rustem.
 */
@Service
public class UserService {
    private AppUserRepository appUserRepository;
    private AppRoleRepository appRoleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(AppUserRepository appUserRepository, AppRoleRepository appRoleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.appUserRepository = appUserRepository;
        this.appRoleRepository = appRoleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void save(RegistrationForm registrationForm) {
        AppRole appRole = appRoleRepository.findByName("ROLE_CLIENT");

        AppUser appUser = new AppUser();
        appUser.setLogin(registrationForm.getLogin());
        appUser.setEmail(registrationForm.getEmail());
        appUser.setHashedPassword(bCryptPasswordEncoder.encode(registrationForm.getPassword()));
        appUser.setAppRole(appRole);

        appUserRepository.save(appUser);
    }

    public AppUser findByLogin(String login) {
        return appUserRepository.findByLogin(login);
    }

    public void addUserToModel(ModelMap model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof AppUser) {
            model.put("user", principal);
        }
    }
}
