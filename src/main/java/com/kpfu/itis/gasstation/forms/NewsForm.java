package com.kpfu.itis.gasstation.forms;

import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;

/**
 * Created by Rustem.
 */
public class NewsForm {
    @NotEmpty(message = "Введите заголовок")
    @Length(max = 40)
    private String header;

    @NotEmpty(message = "Введите новость")
    private String body;

    @NotEmpty(message = "Загрузите фотографию")
    private MultipartFile[] fileDatas;

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

    public MultipartFile[] getFileDatas() {
        return fileDatas;
    }

    public void setFileDatas(MultipartFile[] fileDatas) {
        this.fileDatas = fileDatas;
    }
}
