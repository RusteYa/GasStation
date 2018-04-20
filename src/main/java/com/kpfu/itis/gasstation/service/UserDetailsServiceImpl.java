package com.kpfu.itis.gasstation.service;

/**
 * Created by Rustem.
 */

import com.kpfu.itis.gasstation.entities.AppUser;
import com.kpfu.itis.gasstation.entities.UserRole;
import com.kpfu.itis.gasstation.repositories.AppUserRepository;
import com.kpfu.itis.gasstation.repositories.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private AppUserRepository appUserRepository;
    private UserRoleRepository userRoleRepository;

    @Autowired
    public void setUserRoleRepository(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @Autowired
    public void setAppUserRepository(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        AppUser appUser = this.appUserRepository.findByLogin(login);

        if (appUser == null) {
            System.out.println("AppUser not found! " + login);
            throw new UsernameNotFoundException("AppUser " + login + " was not found in the database");
        }

        System.out.println("Found AppUser: " + appUser);

        // [ROLE_USER, ROLE_ADMIN,..]
        List<UserRole> userRoles = this.userRoleRepository.findByAppUser_Id(appUser.getId());

        List<GrantedAuthority> grantList = new ArrayList<>();
        if (userRoles != null) {
            for (UserRole userRole : userRoles) {
                // ROLE_USER, ROLE_ADMIN,..
                String role = userRole.getAppRole().getName();
                GrantedAuthority authority = new SimpleGrantedAuthority(role);
                grantList.add(authority);
            }
        }

        return new User(appUser.getLogin(), appUser.getPassword(), grantList);
    }
}
