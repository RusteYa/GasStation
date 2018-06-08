package com.kpfu.itis.gasstation.service.entities;

import com.kpfu.itis.gasstation.entities.Ticket;
import com.kpfu.itis.gasstation.forms.TicketRequestForm;
import com.kpfu.itis.gasstation.forms.TicketResponseForm;
import com.kpfu.itis.gasstation.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Rustem.
 */
@Service
public class TicketService {
    private final TicketRepository ticketRepository;
    private final AppUserService appUserService;
    private final TicketStatusService ticketStatusService;

    @Autowired
    public TicketService(TicketRepository ticketRepository, AppUserService appUserService, TicketStatusService ticketStatusService) {
        this.ticketRepository = ticketRepository;
        this.appUserService = appUserService;
        this.ticketStatusService = ticketStatusService;
    }

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public Ticket saveTicketFromTicketRequestForm(TicketRequestForm ticketRequestForm) {
        Ticket ticket = new Ticket();
        ticket.setHeader(ticketRequestForm.getHeader());
        ticket.setRequest(ticketRequestForm.getRequest());
        ticket.setDate(new Date());
        ticket.setTicketStatus(ticketStatusService.getNewTicketStatus());
        ticket.setTicketClient(appUserService.getCurrentAppUser());
        ticket.setTicketStaff(appUserService.getFirstManagerAppUser());

        return ticketRepository.save(ticket);
    }

    public TicketResponseForm createTicketResponseFormFromTicketById(int id) {
        Ticket ticket = ticketRepository.findById(id);

        TicketResponseForm ticketResponseForm = new TicketResponseForm();
        ticketResponseForm.setStatus(ticket.getTicketStatus().getName());
        ticketResponseForm.setResponse(ticket.getResponse());

        return ticketResponseForm;
    }

    public Ticket updateTicketFromTicketResponseFormById(int id, TicketResponseForm ticketResponseForm) {
        Ticket ticket = ticketRepository.findById(id);

        ticket.setResponse(ticketResponseForm.getResponse());
        ticket.setTicketStatus(ticketStatusService.getTicketStatusWithName(ticketResponseForm.getStatus()));

        return ticketRepository.save(ticket);
    }
}
