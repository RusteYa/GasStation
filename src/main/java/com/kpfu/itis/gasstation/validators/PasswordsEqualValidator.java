package com.kpfu.itis.gasstation.validators;

import com.kpfu.itis.gasstation.forms.RegistrationForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by Rustem.
 */
@Component
public class PasswordsEqualValidator implements Validator {
    public boolean supports(Class<?> clazz) {
        return RegistrationForm.class.isAssignableFrom(clazz);
    }

    public void validate(Object target, Errors errors) {
        RegistrationForm registrationForm = (RegistrationForm) target;

        if (!(registrationForm.getPassword()).equals(registrationForm.getConfirmPassword())) {
            errors.rejectValue("confirmPassword", "confirmPassword.passwordDontMatch", "Пароли должны совпадать");
        }
    }
}