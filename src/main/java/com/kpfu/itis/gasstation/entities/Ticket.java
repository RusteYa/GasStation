package com.kpfu.itis.gasstation.entities;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Date;

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
    @Length(max = 40)
    private String header;

    @Column(name = "request")
    @Type(type = "text")
    private String request;

    @Column(name = "response")
    @Type(type = "text")
    private String response;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="date", nullable = false)
    private Date date;

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


    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
