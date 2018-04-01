package com.kpfu.itis.gasstation.controllers;

import com.kpfu.itis.gasstation.entities.Ticket;
import com.kpfu.itis.gasstation.entities.User;
import com.kpfu.itis.gasstation.repositories.TicketRepository;
import com.kpfu.itis.gasstation.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Rustem.
 */
@Controller
@RequestMapping(value = "/tickets")
@SessionAttributes(value = "username")
public class TicketController {
    private TicketRepository ticketRepository;
    private UserRepository userRepository;

    @Autowired
    public void setTicketRepository(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getTickets(ModelMap model) {
        List<Ticket> tickets = ticketRepository.findAll();
        model.put("tickets", tickets);
        return "views/tickets/ticketlist";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getTicket(@PathVariable("id") int id, ModelMap model) {
        Ticket ticket = ticketRepository.findById(id);
        model.put("ticket", ticket);
        return "views/tickets/ticket";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String sendTicket(@RequestParam String body, String header, @RequestParam("username") String username) {
        Ticket ticket = new Ticket();
        ticket.setBody(body);
        ticket.setHeader(header);

        User client = userRepository.findByLogin(username);

        //ticket.setTicketClient(client);

        ticketRepository.save(ticket);
        return "redirect:tickets";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String changeTicket(@PathVariable("id") int id, ModelMap model, @RequestParam String body, String header) {
        Ticket ticket = ticketRepository.findById(id);
        ticket.setBody(body);
        ticket.setHeader(header);
        ticketRepository.deleteTicketById(id);
        ticketRepository.save(ticket);
        model.put("ticket", ticket);
        return "redirect:/tickets/" + id;
    }

    @RequestMapping(value = "/{id}/del", method = RequestMethod.POST)
    public String deleteTicket(@PathVariable("id") int id) {
        ticketRepository.deleteTicketById(id);
        return "redirect:tickets";
    }
}