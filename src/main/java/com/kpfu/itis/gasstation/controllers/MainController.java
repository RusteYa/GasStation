package com.kpfu.itis.gasstation.controllers;

import com.kpfu.itis.gasstation.entities.AppUser;
import com.kpfu.itis.gasstation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

/**
 * Created by Rustem.
 */
@Controller
public class MainController {
    private final UserService userService;

    @Autowired
    public MainController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String main(Principal principal, ModelMap model) {
        if (principal != null) {
            AppUser appUser = userService.findByLogin(principal.getName());
            model.addAttribute("user", appUser);
        }
        return "main";
    }
}
