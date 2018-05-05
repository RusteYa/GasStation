package com.kpfu.itis.gasstation.controllers;

import com.kpfu.itis.gasstation.forms.RegistrationForm;
import com.kpfu.itis.gasstation.service.SecurityService;
import com.kpfu.itis.gasstation.service.UserService;
import com.kpfu.itis.gasstation.validators.RegistrationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.security.Principal;

/**
 * Created by Rustem.
 */
@Controller
public class RegistrationController {
    private UserService userService;
    private SecurityService securityService;

    @Autowired
    public RegistrationController(UserService userService, SecurityService securityService) {
        this.userService = userService;
        this.securityService = securityService;
    }

    @InitBinder(value = "registrationForm")
    protected void initBinder(final WebDataBinder binder) {
        binder.addValidators(new RegistrationValidator());
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(ModelMap model) {
        userService.addUserToModel(model);

        model.addAttribute("registrationForm", new RegistrationForm());
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String addUser(@Valid RegistrationForm registrationForm, BindingResult bindingResult, ModelMap model, Principal principal) {
        if (!bindingResult.hasErrors()) {
            userService.save(registrationForm);
            securityService.autologin(registrationForm.getLogin(), registrationForm.getPassword());
            return "redirect:/secure";
        } else {
            userService.addUserToModel(model);

            model.addAttribute("registrationForm", registrationForm);
            return "register";
        }
    }
}
