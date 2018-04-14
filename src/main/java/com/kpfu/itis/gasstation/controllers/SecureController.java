package com.kpfu.itis.gasstation.controllers;

import com.kpfu.itis.gasstation.forms.RegistrationForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Rustem.
 */
@Controller
public class SecureController {
    @RequestMapping(value = "/secure", method = RequestMethod.GET)
    public String secure(ModelMap model) {
        model.addAttribute("registrationForm", new RegistrationForm());
        return "secure";
    }
}
