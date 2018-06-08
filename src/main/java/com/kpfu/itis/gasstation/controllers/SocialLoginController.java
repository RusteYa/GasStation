package com.kpfu.itis.gasstation.controllers;

import com.kpfu.itis.gasstation.entities.AppUser;
import com.kpfu.itis.gasstation.forms.RegistrationForm;
import com.kpfu.itis.gasstation.service.SecurityService;
import com.kpfu.itis.gasstation.service.entities.AppUserService;
import com.kpfu.itis.gasstation.validators.PasswordsEqualValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

/**
 * Created by Rustem.
 */
@Controller
@Transactional
public class SocialLoginController {
    private final AppUserService appUserService;
    private final ConnectionFactoryLocator connectionFactoryLocator;
    private final UsersConnectionRepository connectionRepository;
    @Autowired
    private SecurityService securityService;

    @Autowired
    public SocialLoginController(AppUserService appUserService, ConnectionFactoryLocator connectionFactoryLocator, UsersConnectionRepository connectionRepository) {
        this.appUserService = appUserService;
        this.connectionFactoryLocator = connectionFactoryLocator;
        this.connectionRepository = connectionRepository;
    }

    @InitBinder(value = "registrationForm")
    protected void initBinder(final WebDataBinder binder) {
        binder.addValidators(new PasswordsEqualValidator());
    }

    @RequestMapping(value = {"/signup"}, method = RequestMethod.GET)
    public String signupPage(WebRequest request, Model model) {
        ProviderSignInUtils providerSignInUtils = new ProviderSignInUtils(connectionFactoryLocator, connectionRepository);
        Connection<?> connection = providerSignInUtils.getConnectionFromSession(request);
        RegistrationForm registrationForm;
        if (connection != null) {
            registrationForm = new RegistrationForm(connection);
        } else {
            registrationForm = new RegistrationForm();
        }
        model.addAttribute("registrationForm", registrationForm);
        return "social_register";
    }

    @RequestMapping(value = {"/signup"}, method = RequestMethod.POST)
    public String signupSave(WebRequest request, Model model, @ModelAttribute("registrationForm") @Validated RegistrationForm registrationForm, //
                             BindingResult result) {
        if (result.hasErrors()) {
            return "social_register";
        }

        AppUser registered;
        try {
            registered = appUserService.saveAppUserFromRegistrationForm(registrationForm);
        } catch (Exception ex) {
            ex.printStackTrace();
            model.addAttribute("errorMessage", "Error " + ex.getMessage());
            return "social_register";
        }

        if (registrationForm.getSignInProvider() != null) {
            ProviderSignInUtils providerSignInUtils = new ProviderSignInUtils(connectionFactoryLocator, connectionRepository);
            providerSignInUtils.doPostSignUp(registered.getLogin(), request);
        }

        securityService.autologin(registrationForm.getLogin(), registrationForm.getPassword());
        return "redirect:/";
    }
}