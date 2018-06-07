package com.kpfu.itis.gasstation.controllers;

import com.kpfu.itis.gasstation.forms.MessageForm;
import com.kpfu.itis.gasstation.service.entities_service.AppUserService;
import com.kpfu.itis.gasstation.service.entities_service.MessageService;
import com.kpfu.itis.gasstation.validators.HasLoginValidator;
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
public class MessageController {
    private final MessageService messageService;
    private final AppUserService appUserService;

    @Autowired
    public MessageController(AppUserService appUserService, MessageService messageService) {
        this.appUserService = appUserService;
        this.messageService = messageService;
    }

    @InitBinder(value = "messageForm")
    protected void initBinder(final WebDataBinder binder) {
        binder.addValidators(new HasLoginValidator(appUserService));
    }

    @RequestMapping(value = "/messages", method = RequestMethod.GET)
    public String messages(ModelMap model, Principal principal) {
        model.put("user", appUserService.getAppUserByLogin(principal.getName()));
        return "messages";
    }

    @RequestMapping(value = "/messages/send", method = RequestMethod.GET)
    public String sendMessage(ModelMap model) {
        MessageForm messageForm = new MessageForm();
        model.put("messageForm", messageForm);
        return "send_message";
    }

    @RequestMapping(value = "/messages/send", method = RequestMethod.POST)
    public String sendMessage(@Valid MessageForm messageForm, BindingResult bindingResult, ModelMap model) {
        if (!bindingResult.hasErrors()) {
            messageService.saveMessageFromMessageForm(messageForm);
            return "redirect:/messages";
        } else {
            model.put("newsForm", messageForm);
            return "send_message";
        }
    }
}