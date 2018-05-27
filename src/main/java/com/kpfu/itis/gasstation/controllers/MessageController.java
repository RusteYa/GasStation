package com.kpfu.itis.gasstation.controllers;

import com.kpfu.itis.gasstation.entities.Message;
import com.kpfu.itis.gasstation.repositories.MessageRepository;
import com.kpfu.itis.gasstation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Rustem.
 */
@Controller
public class MessageController {
    private final MessageRepository messageRepository;
    private final UserService userService;

    @Autowired
    public MessageController(MessageRepository messageRepository, UserService userService) {
        this.messageRepository = messageRepository;
        this.userService = userService;
    }

    @RequestMapping(value = "/messages", method = RequestMethod.GET)
    public String messages(ModelMap model) {
        userService.addUserToModel(model);
        return "messages";
    }























    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getMessage(@PathVariable("id") int id, ModelMap model) {
        Message message = messageRepository.findById(id);
        model.put("message", message);
        return "views/messages/message";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String addMessage(@RequestParam String body) {
        Message message = new Message();
        message.setBody(body);
        messageRepository.save(message);
        return "redirect:messages";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String changeMessage(@PathVariable("id") int id, ModelMap model, @RequestParam String body) {
        Message message = messageRepository.findById(id);
        message.setBody(body);
        messageRepository.deleteMessageById(id);
        messageRepository.save(message);
        model.put("message", message);
        return "redirect:/messages/" + id;
    }

    @RequestMapping(value = "/{id}/del", method = RequestMethod.POST)
    public String deleteMessage(@PathVariable("id") int id) {
        messageRepository.deleteMessageById(id);
        return "redirect:messages";
    }
}