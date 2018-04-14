package com.kpfu.itis.gasstation.entities;

import javax.persistence.*;
import java.sql.Timestamp;

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
    private String body;

    @Column(name = "date")
    private Timestamp date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sender_id", nullable = false)
    private AppUser messageSender;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "receiver_id", nullable = false)
    private AppUser messageReceiver;

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
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
}
