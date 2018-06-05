package com.kpfu.itis.gasstation.controllers;

import com.kpfu.itis.gasstation.service.entities_service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Rustem.
 */
@Controller
public class AccessDeniedController {
    private AppUserService appUserService;

    @Autowired
    public AccessDeniedController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @RequestMapping(value = "/access-denied", method = RequestMethod.GET)
    public String accessDenied(ModelMap model) {
        appUserService.addAppUserToModel(model);
        return "access_denied";
    }
}
