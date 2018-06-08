package com.kpfu.itis.gasstation.service_test;

import com.kpfu.itis.gasstation.entities.AppUser;
import com.kpfu.itis.gasstation.entities.Message;
import com.kpfu.itis.gasstation.forms.MessageForm;
import com.kpfu.itis.gasstation.repositories.MessageRepository;
import com.kpfu.itis.gasstation.service.entities_service.AppUserService;
import com.kpfu.itis.gasstation.service.entities_service.MessageService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Created by Rustem.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageServiceTest {
    @Autowired
    private MessageService messageService;

    @MockBean
    private MessageRepository messageRepositoryMock;
    @MockBean
    private AppUserService appUserServiceMock;

    @Test
    public void testSaveMessageFromMessageForm() {
        Message message = new Message();

        when(appUserServiceMock.getCurrentAppUser()).thenReturn(new AppUser());
        when(messageRepositoryMock.save(any(Message.class))).thenReturn(message);

        MessageForm messageForm = new MessageForm();
        Message message1 = messageService.saveMessageFromMessageForm(messageForm);
        Assert.assertEquals(messageForm.getHeader(), message1.getHeader());
    }
}
