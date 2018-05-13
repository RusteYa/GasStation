package com.kpfu.itis.gasstation.forms;

import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

/**
 * Created by Rustem.
 */
public class EngineOilForm {
    @NotEmpty(message = "Введите название моторного масла")
    @Length(max = 60)
    private String name;

    @Min(value = 1, message = "Введите цену моторного масла")
    private int price;

    @NotEmpty(message = "Введите название компании-производителя")
    @Length(max = 60)
    private String manafacturer;

    private MultipartFile[] fileDatas;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getManafacturer() {
        return manafacturer;
    }

    public void setManafacturer(String manafacturer) {
        this.manafacturer = manafacturer;
    }

    public MultipartFile[] getFileDatas() {
        return fileDatas;
    }

    public void setFileDatas(MultipartFile[] fileDatas) {
        this.fileDatas = fileDatas;
    }
}
