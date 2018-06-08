package com.kpfu.itis.gasstation.controllers;

import com.kpfu.itis.gasstation.forms.RegistrationForm;
import com.kpfu.itis.gasstation.service.SecurityService;
import com.kpfu.itis.gasstation.service.entities.AppUserService;
import com.kpfu.itis.gasstation.validators.PasswordsEqualValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by Rustem.
 */
@Controller
public class RegistrationController {
    private AppUserService appUserService;
    private SecurityService securityService;

    @Autowired
    public RegistrationController(AppUserService appUserService, SecurityService securityService) {
        this.appUserService = appUserService;
        this.securityService = securityService;
    }

    @InitBinder(value = "registrationForm")
    protected void initBinder(final WebDataBinder binder) {
        binder.addValidators(new PasswordsEqualValidator());
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(ModelMap model) {
        model.addAttribute("registrationForm", new RegistrationForm());
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String addUser(@Valid RegistrationForm registrationForm, BindingResult bindingResult, ModelMap model) {
        if (!bindingResult.hasErrors()) {
            appUserService.saveAppUserFromRegistrationForm(registrationForm);
            securityService.autologin(registrationForm.getLogin(), registrationForm.getPassword());
            return "redirect:/";
        } else {
            model.addAttribute("registrationForm", registrationForm);
            return "register";
        }
    }
}
