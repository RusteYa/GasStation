package com.kpfu.itis.gasstation.service.entities;

import com.kpfu.itis.gasstation.entities.AppRole;
import com.kpfu.itis.gasstation.repositories.AppRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Rustem.
 */
@Service
public class AppRoleService {
    private final AppRoleRepository appRoleRepository;

    public final static String ROLE_CLIENT = "ROLE_CLIENT";
    public final static String ROLE_MANAGER = "ROLE_MANAGER";

    @Autowired
    public AppRoleService(AppRoleRepository appRoleRepository) {
        this.appRoleRepository = appRoleRepository;
    }

    public List<AppRole> getAllAppRoles() {
        return appRoleRepository.findAll();
    }

    public AppRole getAppRoleWithName(String name) {
        return appRoleRepository.findByName(name);
    }

    public AppRole getClientAppRole() {
        return appRoleRepository.findByName(ROLE_CLIENT);
    }
}
