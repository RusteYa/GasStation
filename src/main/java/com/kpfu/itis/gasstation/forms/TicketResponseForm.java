package com.kpfu.itis.gasstation.forms;

import javax.validation.constraints.NotEmpty;

/**
 * Created by Rustem.
 */
public class TicketResponseForm {
    @NotEmpty(message = "Выберите статус")
    private String status;

    @NotEmpty(message = "Введите ответ на заявку")
    private String response;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
