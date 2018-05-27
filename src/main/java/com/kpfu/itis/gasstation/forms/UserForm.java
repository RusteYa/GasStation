package com.kpfu.itis.gasstation.forms;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

/**
 * Created by Rustem.
 */
public class UserForm {
    @NotEmpty(message = "Введите имя")
    @Length(max = 30)
    private String name;

    @NotEmpty(message = "Выберите роль")
    private String role;

    @NotEmpty(message = "Введите логин")
    @Length(max = 30)
    private String login;

    @NotEmpty(message = "Введите email")
    @Email(message = "Некорректный email")
    private String email;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
