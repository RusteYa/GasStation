package com.kpfu.itis.gasstation.forms;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

/**
 * Created by Rustem.
 */
public class MessageForm {
    @NotEmpty(message = "Введите логин получателя")
    private String recipientLogin;

    @NotEmpty(message = "Введите заголовок")
    @Length(max = 40)
    private String header;

    @NotEmpty(message = "Введите сообщение")
    private String body;

    public String getRecipientLogin() {
        return recipientLogin;
    }

    public void setRecipientLogin(String recipientLogin) {
        this.recipientLogin = recipientLogin;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
