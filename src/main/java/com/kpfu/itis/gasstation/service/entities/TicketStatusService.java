package com.kpfu.itis.gasstation.service.entities;

import com.kpfu.itis.gasstation.entities.TicketStatus;
import com.kpfu.itis.gasstation.repositories.TicketStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Rustem.
 */
@Service
public class TicketStatusService {
    private final TicketStatusRepository ticketStatusRepository;

    public final static String STATUS_NEW = "NEW";

    @Autowired
    public TicketStatusService(TicketStatusRepository ticketStatusRepository) {
        this.ticketStatusRepository = ticketStatusRepository;
    }

    public TicketStatus getNewTicketStatus() {
        return ticketStatusRepository.findByName(STATUS_NEW);
    }

    public TicketStatus getTicketStatusWithName(String name) {
        return ticketStatusRepository.findByName(name);
    }

    public List<TicketStatus> getAllTicketStatuses() {
        return ticketStatusRepository.findAll();
    }
}
