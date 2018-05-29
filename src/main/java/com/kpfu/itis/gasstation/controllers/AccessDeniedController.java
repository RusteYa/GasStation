package com.kpfu.itis.gasstation.controllers;

import com.kpfu.itis.gasstation.service.UserService;
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
    private UserService userService;

    @Autowired
    public AccessDeniedController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/access-denied", method = RequestMethod.GET)
    public String accessDenied(ModelMap model) {
        userService.addUserToModel(model);
        return "access_denied";
    }
}
