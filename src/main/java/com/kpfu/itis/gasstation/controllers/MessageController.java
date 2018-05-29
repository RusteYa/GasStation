package com.kpfu.itis.gasstation.controllers;

import com.kpfu.itis.gasstation.entities.Message;
import com.kpfu.itis.gasstation.forms.MessageForm;
import com.kpfu.itis.gasstation.repositories.AppUserRepository;
import com.kpfu.itis.gasstation.repositories.MessageRepository;
import com.kpfu.itis.gasstation.service.UserService;
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
import java.util.Date;

/**
 * Created by Rustem.
 */
@Controller
public class MessageController {
    private final MessageRepository messageRepository;
    private final UserService userService;
    private final AppUserRepository appUserRepository;

    @Autowired
    public MessageController(MessageRepository messageRepository, UserService userService, AppUserRepository appUserRepository) {
        this.messageRepository = messageRepository;
        this.userService = userService;
        this.appUserRepository = appUserRepository;
    }

    @InitBinder(value = "messageForm")
    protected void initBinder(final WebDataBinder binder) {
        binder.addValidators(new HasLoginValidator(appUserRepository));
    }

    @RequestMapping(value = "/messages", method = RequestMethod.GET)
    public String messages(ModelMap model, Principal principal) {
        model.put("user", appUserRepository.findByLogin(principal.getName()));
        return "messages";
    }

    @RequestMapping(value = "/messages/send", method = RequestMethod.GET)
    public String sendMessage(ModelMap model) {
        userService.addUserToModel(model);
        MessageForm messageForm = new MessageForm();
        model.put("messageForm", messageForm);
        return "send_message";
    }

    @RequestMapping(value = "/messages/send", method = RequestMethod.POST)
    public String sendMessage(@Valid MessageForm messageForm, BindingResult bindingResult, ModelMap model) {
        if (!bindingResult.hasErrors()) {
            Message message = new Message();
            message.setMessageReceiver(appUserRepository.findByLogin(messageForm.getRecipientLogin()));
            message.setMessageSender(userService.getCurrentUser());
            message.setHeader(messageForm.getHeader());
            message.setBody(messageForm.getBody());
            message.setDate(new Date());

            messageRepository.save(message);

            return "redirect:/messages";
        } else {
            userService.addUserToModel(model);
            model.put("newsForm", messageForm);
            return "send_message";
        }
    }
}