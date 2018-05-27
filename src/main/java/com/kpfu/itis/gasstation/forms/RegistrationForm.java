package com.kpfu.itis.gasstation.forms;


import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * Created by Rustem.
 */
public class RegistrationForm {
    @NotEmpty(message = "Введите логин")
    @Length(max = 30)
    private String login;

    @NotEmpty(message = "Введите email")
    @Email(message = "Некорректный email")
    private String email;

    @NotEmpty(message = "Введите имя")
    @Length(max = 30)
    private String name;

    @NotEmpty(message = "Введите пароль")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\\s).*$", message = "Пароль должен содержать цифры и буквы в верхней и нижней раскладках")
    private String password;

    @NotEmpty(message = "Введите подтверждение пароля")
    private String confirmPassword;

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
