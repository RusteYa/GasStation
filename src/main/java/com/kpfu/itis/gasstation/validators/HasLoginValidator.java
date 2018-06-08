package com.kpfu.itis.gasstation.validators;

import com.kpfu.itis.gasstation.forms.MessageForm;
import com.kpfu.itis.gasstation.service.entities.AppUserService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by Rustem.
 */
@Component
public class HasLoginValidator implements Validator {
    private AppUserService appUserService;

    public HasLoginValidator(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    public boolean supports(Class<?> clazz) {
        return MessageForm.class.isAssignableFrom(clazz);
    }

    public void validate(Object target, Errors errors) {
        MessageForm messageForm = (MessageForm) target;

        if (appUserService.getAppUserByLogin(messageForm.getRecipientLogin()) == null) {
            errors.rejectValue("recipientLogin", "recipientLogin.loginDontMatch", "Пользователя с таким логином не существует");
        }
    }
}