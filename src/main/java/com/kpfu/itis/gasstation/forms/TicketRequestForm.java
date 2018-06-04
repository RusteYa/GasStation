package com.kpfu.itis.gasstation.forms;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

/**
 * Created by Rustem.
 */
public class TicketRequestForm {
    @NotEmpty(message = "Введите заголовок")
    @Length(max = 40)
    private String header;

    @NotEmpty(message = "Введите запрос заявки")
    private String request;

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }
}
