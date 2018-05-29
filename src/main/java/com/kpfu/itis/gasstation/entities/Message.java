package com.kpfu.itis.gasstation.entities;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Rustem.
 */
@Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "header", length = 40)
    private String header;

    @Column(name = "body")
    @Type(type = "text")
    private String body;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="date", nullable = false)
    private Date date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sender_id", nullable = false)
    private AppUser messageSender;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "receiver_id", nullable = false)
    private AppUser messageReceiver;

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

    public void setBody(String body) {
        this.body = body;
    }
    public AppUser getMessageSender() {
        return messageSender;
    }

    public void setMessageSender(AppUser messageSender) {
        this.messageSender = messageSender;
    }

    public AppUser getMessageReceiver() {
        return messageReceiver;
    }

    public void setMessageReceiver(AppUser messageReceiver) {
        this.messageReceiver = messageReceiver;
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
