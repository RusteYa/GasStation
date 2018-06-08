package com.kpfu.itis.gasstation.service.social;

import com.kpfu.itis.gasstation.entities.AppUser;
import com.kpfu.itis.gasstation.service.entities.AppUserService;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;

/**
 * Created by Rustem.
 */
public class ConnectionSignUpImpl implements ConnectionSignUp {
    private AppUserService appUserService;

    public ConnectionSignUpImpl(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @Override
    public String execute(Connection<?> connection) {
        AppUser account = appUserService.createAppUser(connection);
        return account.getLogin();
    }
}