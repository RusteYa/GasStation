package com.kpfu.itis.gasstation.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Rustem.
 */
@Entity
@Table(name = "ticketstatuses")
public class TicketStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name", unique = true, nullable = false, length = 20)
    private String name;

    @OneToMany(mappedBy = "ticketStatus", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JsonIgnore
    private Collection<Ticket> ticketsWithStatus;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Ticket> getTicketsWithStatus() {
        return ticketsWithStatus;
    }

    public void setTicketsWithStatus(Collection<Ticket> ticketsWithStatus) {
        this.ticketsWithStatus = ticketsWithStatus;
    }
}
