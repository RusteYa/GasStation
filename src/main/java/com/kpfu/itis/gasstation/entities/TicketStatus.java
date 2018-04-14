package com.kpfu.itis.gasstation.entities;

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

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "ticketStatus", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
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
