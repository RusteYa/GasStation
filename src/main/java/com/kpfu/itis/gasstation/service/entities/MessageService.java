package com.kpfu.itis.gasstation.service.entities;

import com.kpfu.itis.gasstation.entities.Message;
import com.kpfu.itis.gasstation.forms.MessageForm;
import com.kpfu.itis.gasstation.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by Rustem.
 */
@Service
public class MessageService {
    private final MessageRepository messageRepository;
    private final AppUserService appUserService;

    @Autowired
    public MessageService(MessageRepository messageRepository, AppUserService appUserService) {
        this.messageRepository = messageRepository;
        this.appUserService = appUserService;
    }

    public Message saveMessageFromMessageForm(MessageForm messageForm) {
        Message message = new Message();
        message.setMessageReceiver(appUserService.getAppUserByLogin(messageForm.getRecipientLogin()));
        message.setMessageSender(appUserService.getCurrentAppUser());
        message.setHeader(messageForm.getHeader());
        message.setBody(messageForm.getBody());
        message.setDate(new Date());

        return messageRepository.save(message);
    }
}
