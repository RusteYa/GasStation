package controllers;

import entities.Message;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import repositories.MessageRepository;
import helpers.AppContext;

import java.util.List;

/**
 * Created by Rustem.
 */
@Controller
@RequestMapping(value = "/messages")
public class MessageController {
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getMessages(ModelMap model) {
        MessageRepository messageRepository = AppContext.getApplicationContext().getBean(MessageRepository.class);
        List<Message> messages = messageRepository.findAll();
        model.put("messages", messages);
        return "views/messages/messagelist";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getMessage(@PathVariable("id") int id, ModelMap model) {
        MessageRepository messageRepository = AppContext.getApplicationContext().getBean(MessageRepository.class);
        Message message = messageRepository.findById(id);
        model.put("message", message);
        return "views/messages/message";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String addMessage(@RequestParam String body) {
        MessageRepository messageRepository = AppContext.getApplicationContext().getBean(MessageRepository.class);
        Message message = new Message();
        message.setBody(body);
        messageRepository.save(message);
        return "redirect:messages";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String changeMessage(@PathVariable("id") int id, ModelMap model, @RequestParam String body) {
        MessageRepository messageRepository = AppContext.getApplicationContext().getBean(MessageRepository.class);
        Message message = messageRepository.findById(id);
        message.setBody(body);
        messageRepository.deleteMessageById(id);
        messageRepository.save(message);
        model.put("message", message);
        return "redirect:/messages/" + id;
    }

    @RequestMapping(value = "/{id}/del", method = RequestMethod.POST)
    public String deleteMessage(@PathVariable("id") int id) {
        MessageRepository messageRepository = AppContext.getApplicationContext().getBean(MessageRepository.class);
        messageRepository.deleteMessageById(id);
        return "redirect:messages";
    }
}