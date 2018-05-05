package com.kpfu.itis.gasstation.service;

/**
 * Created by Rustem.
 */

import com.kpfu.itis.gasstation.entities.AppUser;
import com.kpfu.itis.gasstation.repositories.AppUserRepository;
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
    private final AppUserRepository appUserRepository;

    @Autowired
    public UserDetailsServiceImpl(AppUserRepository appUserRepository) {
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

        List<GrantedAuthority> grantList = new ArrayList<>();
        String role = appUser.getAppRole().getName();
        GrantedAuthority authority = new SimpleGrantedAuthority(role);
        grantList.add(authority);
        User user = new User(appUser.getLogin(), appUser.getHashedPassword(), grantList);

        appUser.setUser(user);

        return appUser;
    }
}
