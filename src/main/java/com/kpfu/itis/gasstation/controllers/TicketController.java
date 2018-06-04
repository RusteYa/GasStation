package com.kpfu.itis.gasstation.controllers;

import com.kpfu.itis.gasstation.entities.Ticket;
import com.kpfu.itis.gasstation.entities.TicketStatus;
import com.kpfu.itis.gasstation.forms.TicketRequestForm;
import com.kpfu.itis.gasstation.forms.TicketResponseForm;
import com.kpfu.itis.gasstation.repositories.AppUserRepository;
import com.kpfu.itis.gasstation.repositories.TicketRepository;
import com.kpfu.itis.gasstation.repositories.TicketStatusRepository;
import com.kpfu.itis.gasstation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Rustem.
 */
@Controller
public class TicketController {
    private final TicketRepository ticketRepository;
    private final AppUserRepository appUserRepository;
    private final UserService userService;
    private final TicketStatusRepository ticketStatusRepository;

    @Autowired
    public TicketController(TicketRepository ticketRepository, AppUserRepository appUserRepository, UserService userService, TicketStatusRepository ticketStatusRepository) {
        this.ticketRepository = ticketRepository;
        this.appUserRepository = appUserRepository;
        this.userService = userService;
        this.ticketStatusRepository = ticketStatusRepository;
    }

    @RequestMapping(value = "/tickets", method = RequestMethod.GET)
    public String getTickets(ModelMap model) {
        userService.addUserToModel(model);
        List<Ticket> ticketlist = ticketRepository.findAll();
        model.put("ticketlist", ticketlist);
        return "tickets";
    }

    @RequestMapping(value = "/tickets/add", method = RequestMethod.GET)
    public String addTicket(ModelMap model) {
        userService.addUserToModel(model);
        TicketRequestForm ticketRequestForm = new TicketRequestForm();
        model.put("ticketRequestForm", ticketRequestForm);
        return "create_ticket";
    }

    @RequestMapping(value = "/tickets/add", method = RequestMethod.POST)
    public String addTicket(@Valid TicketRequestForm ticketRequestForm, BindingResult bindingResult, ModelMap model) {
        if (!bindingResult.hasErrors()) {
            Ticket ticket = new Ticket();
            ticket.setHeader(ticketRequestForm.getHeader());
            ticket.setRequest(ticketRequestForm.getRequest());
            ticket.setDate(new Date());
            ticket.setTicketStatus(ticketStatusRepository.findByName("NEW"));
            ticket.setTicketClient(userService.getCurrentUser());
            ticket.setTicketStaff(appUserRepository.findFirstByAppRoleName("ROLE_MANAGER"));

            ticketRepository.save(ticket);

            return "redirect:/tickets";
        } else {
            userService.addUserToModel(model);
            model.put("ticketRequestForm", ticketRequestForm);
            return "create_ticket";
        }
    }

    @RequestMapping(value = "/manager/ticket/{id}", method = RequestMethod.GET)
    public String reviewTicket(@PathVariable("id") int id, ModelMap model) {
        Ticket ticket = ticketRepository.findById(id);

        TicketResponseForm ticketResponseForm = new TicketResponseForm();
        ticketResponseForm.setStatus(ticket.getTicketStatus().getName());
        ticketResponseForm.setResponse(ticket.getResponse());

        List<String> statuses = ticketStatusRepository.findAll().stream().map(TicketStatus::getName).collect(Collectors.toList());

        model.put("statuses", statuses);
        model.put("ticketResponseForm", ticketResponseForm);

        return "review_ticket";
    }

    @RequestMapping(value = "/manager/ticket/{id}", method = RequestMethod.POST)
    public String updateUser(@PathVariable("id") int id, ModelMap model, @Valid TicketResponseForm ticketResponseForm, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            Ticket ticket = ticketRepository.findById(id);

            ticket.setResponse(ticketResponseForm.getResponse());
            ticket.setTicketStatus(ticketStatusRepository.findByName(ticketResponseForm.getStatus()));

            ticketRepository.save(ticket);

            return "redirect:/tickets";
        } else {
            List<String> statuses = ticketStatusRepository.findAll().stream().map(TicketStatus::getName).collect(Collectors.toList());
            model.put("statuses", statuses);
            model.put("ticketResponseForm", ticketResponseForm);
            return "review_ticket";
        }
    }
}