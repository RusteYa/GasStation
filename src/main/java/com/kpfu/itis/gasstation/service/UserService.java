package com.kpfu.itis.gasstation.service;

import com.kpfu.itis.gasstation.entities.AppRole;
import com.kpfu.itis.gasstation.entities.AppUser;
import com.kpfu.itis.gasstation.entities.UserRole;
import com.kpfu.itis.gasstation.forms.RegistrationForm;
import com.kpfu.itis.gasstation.repositories.AppRoleRepository;
import com.kpfu.itis.gasstation.repositories.AppUserRepository;
import com.kpfu.itis.gasstation.repositories.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by Rustem.
 */
@Service
public class UserService {
    private AppUserRepository appUserRepository;
    private UserRoleRepository userRoleRepository;
    private AppRoleRepository appRoleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(AppUserRepository appUserRepository, UserRoleRepository userRoleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.appUserRepository = appUserRepository;
        this.userRoleRepository = userRoleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void save(RegistrationForm registrationForm) {
        AppUser appUser = new AppUser();
        appUser.setLogin(registrationForm.getLogin());
        appUser.setEmail(registrationForm.getEmail());
        appUser.setPassword(bCryptPasswordEncoder.encode(registrationForm.getPassword()));
        appUserRepository.save(appUser);

        AppRole appRole = appRoleRepository.findByName("ROLE_CLIENT");

        UserRole userRole = new UserRole();
        userRole.setAppUser(appUser);
        userRole.setAppRole(appRole);
        userRoleRepository.save(userRole);
    }

    public AppUser findByLogin(String login) {
        return appUserRepository.findByLogin(login);
    }
}
