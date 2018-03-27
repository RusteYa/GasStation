package controllers;

import entities.Ticket;
import entities.User;
import helpers.AppContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import repositories.TicketRepository;
import repositories.UserRepository;

import java.util.List;

/**
 * Created by Rustem.
 */
@Controller
@RequestMapping(value = "/tickets")
@SessionAttributes(value = "username")
public class TicketController {
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getTickets(ModelMap model) {
        TicketRepository ticketRepository = AppContext.getApplicationContext().getBean(TicketRepository.class);
        List<Ticket> tickets = ticketRepository.findAll();
        model.put("tickets", tickets);
        return "views/tickets/ticketlist";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getTicket(@PathVariable("id") int id, ModelMap model) {
        TicketRepository ticketRepository = AppContext.getApplicationContext().getBean(TicketRepository.class);
        Ticket ticket = ticketRepository.findById(id);
        model.put("ticket", ticket);
        return "views/tickets/ticket";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String sendTicket(@RequestParam String body, String header, @RequestParam("username") String username) {
        TicketRepository ticketRepository = AppContext.getApplicationContext().getBean(TicketRepository.class);
        Ticket ticket = new Ticket();
        ticket.setBody(body);
        ticket.setHeader(header);

        UserRepository userRepository = AppContext.getApplicationContext().getBean(UserRepository.class);
        User client = userRepository.findByLogin(username);

        ticket.setUserByIdClient(client);
        ticket.setIdClient(client.getId());

        ticketRepository.save(ticket);
        return "redirect:tickets";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String changeTicket(@PathVariable("id") int id, ModelMap model, @RequestParam String body, String header) {
        TicketRepository ticketRepository = AppContext.getApplicationContext().getBean(TicketRepository.class);
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
        TicketRepository ticketRepository = AppContext.getApplicationContext().getBean(TicketRepository.class);
        ticketRepository.deleteTicketById(id);
        return "redirect:tickets";
    }
}