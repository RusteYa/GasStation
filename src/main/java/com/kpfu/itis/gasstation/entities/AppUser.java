package com.kpfu.itis.gasstation.entities;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Rustem.
 */
@Entity
@Table(name = "appusers")
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "login", unique = true, nullable = false, length = 40)
    private String login;

    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @Column(name = "name", length = 45)
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "deposit")
    private int deposit;

    @OneToMany(mappedBy = "messageSender", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Message> sentMessages;

    @OneToMany(mappedBy = "messageReceiver", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Message> receivedMessages;

    @OneToMany(mappedBy = "ticketClient", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Ticket> clientTickets;

    @OneToMany(mappedBy = "ticketStaff", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Ticket> staffTickets;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", deposit=" + deposit +
                ", sentMessages=" + sentMessages +
                ", receivedMessages=" + receivedMessages +
                ", clientTickets=" + clientTickets +
                ", staffTickets=" + staffTickets +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDeposit() {
        return deposit;
    }

    public void setDeposit(int deposit) {
        this.deposit = deposit;
    }

    public List<Message> getSentMessages() {
        return sentMessages;
    }

    public void setSentMessages(List<Message> sentMessages) {
        this.sentMessages = sentMessages;
    }

    public List<Message> getReceivedMessages() {
        return receivedMessages;
    }

    public void setReceivedMessages(List<Message> receivedMessages) {
        this.receivedMessages = receivedMessages;
    }

    public List<Ticket> getClientTickets() {
        return clientTickets;
    }

    public void setClientTickets(List<Ticket> clientTickets) {
        this.clientTickets = clientTickets;
    }

    public List<Ticket> getStaffTickets() {
        return staffTickets;
    }

    public void setStaffTickets(List<Ticket> staffTickets) {
        this.staffTickets = staffTickets;
    }
}
