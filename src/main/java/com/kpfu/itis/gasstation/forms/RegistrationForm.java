package com.kpfu.itis.gasstation.forms;


import org.hibernate.validator.constraints.Length;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.connect.UserProfile;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * Created by Rustem.
 */
public class RegistrationForm {
    @NotEmpty(message = "Введите логин")
    @Length(max = 40)
    private String login;

    @NotEmpty(message = "Введите email")
    @Email(message = "Некорректный email")
    @Length(max = 128)
    private String email;

    @NotEmpty(message = "Введите имя")
    @Length(max = 40)
    private String name;

    @NotEmpty(message = "Введите пароль")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\\s).*$", message = "Пароль должен содержать цифры и буквы в верхней и нижней раскладках")
    private String password;

    @NotEmpty(message = "Введите подтверждение пароля")
    private String confirmPassword;

    private String signInProvider;
    private String providerUserId;

    public RegistrationForm() {
    }

    public RegistrationForm(Connection<?> connection) {
        UserProfile socialUserProfile = connection.fetchUserProfile();
        ConnectionKey key = connection.getKey();
        this.email = socialUserProfile.getEmail();
        this.login = socialUserProfile.getUsername();
        this.name = socialUserProfile.getFirstName() + " " + socialUserProfile.getLastName();
        this.signInProvider = key.getProviderId();
        this.providerUserId = key.getProviderUserId();
    }

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

    public String getSignInProvider() {
        return signInProvider;
    }

    public void setSignInProvider(String signInProvider) {
        this.signInProvider = signInProvider;
    }

    public String getProviderUserId() {
        return providerUserId;
    }

    public void setProviderUserId(String providerUserId) {
        this.providerUserId = providerUserId;
    }
}
