package com.kpfu.itis.gasstation.entities;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Rustem.
 */
@Entity
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "header")
    private String header;

    @Column(name = "body")
    private String body;

    @Column(name = "date", nullable = true)
    private Timestamp date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ticketstatuses_id", nullable = false)
    private TicketStatus ticketStatus;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id", nullable = false)
    private AppUser ticketClient;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "staff_id", nullable = false)
    private AppUser ticketStaff;

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

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public TicketStatus getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(TicketStatus ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    public AppUser getTicketClient() {
        return ticketClient;
    }

    public void setTicketClient(AppUser ticketClient) {
        this.ticketClient = ticketClient;
    }

    public AppUser getTicketStaff() {
        return ticketStaff;
    }

    public void setTicketStaff(AppUser ticketStaff) {
        this.ticketStaff = ticketStaff;
    }
}
