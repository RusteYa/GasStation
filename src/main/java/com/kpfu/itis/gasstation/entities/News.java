package com.kpfu.itis.gasstation.entities;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Rustem.
 */
@Entity
@Table(name = "news")
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", insertable=false, nullable = false)
    private int id;

    @Column(name = "header", length = 40, nullable = false)
    private String header;

    @Column(name = "body", nullable = false)
    private String body;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="date", nullable = false)
    private Date date;

    @Column(name = "photopath", nullable = false)
    private String photoPath;

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
