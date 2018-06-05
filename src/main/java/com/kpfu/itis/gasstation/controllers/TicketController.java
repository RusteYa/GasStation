package com.kpfu.itis.gasstation.controllers;

import com.kpfu.itis.gasstation.entities.Ticket;
import com.kpfu.itis.gasstation.entities.TicketStatus;
import com.kpfu.itis.gasstation.forms.TicketRequestForm;
import com.kpfu.itis.gasstation.forms.TicketResponseForm;
import com.kpfu.itis.gasstation.service.entities_service.AppUserService;
import com.kpfu.itis.gasstation.service.entities_service.TicketService;
import com.kpfu.itis.gasstation.service.entities_service.TicketStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Rustem.
 */
@Controller
public class TicketController {
    private final TicketService ticketService;
    private final TicketStatusService ticketStatusService;
    private final AppUserService appUserService;


    @Autowired
    public TicketController(AppUserService appUserService, TicketService ticketService, TicketStatusService ticketStatusService) {
        this.appUserService = appUserService;
        this.ticketService = ticketService;
        this.ticketStatusService = ticketStatusService;
    }

    @RequestMapping(value = "/tickets", method = RequestMethod.GET)
    public String getTickets(ModelMap model) {
        appUserService.addAppUserToModel(model);
        List<Ticket> ticketlist = ticketService.getAllTickets();
        model.put("ticketlist", ticketlist);
        return "tickets";
    }

    @RequestMapping(value = "/tickets/add", method = RequestMethod.GET)
    public String addTicket(ModelMap model) {
        appUserService.addAppUserToModel(model);
        TicketRequestForm ticketRequestForm = new TicketRequestForm();
        model.put("ticketRequestForm", ticketRequestForm);
        return "create_ticket";
    }

    @RequestMapping(value = "/tickets/add", method = RequestMethod.POST)
    public String addTicket(@Valid TicketRequestForm ticketRequestForm, BindingResult bindingResult, ModelMap model) {
        if (!bindingResult.hasErrors()) {
            ticketService.saveTicketFromTicketRequestForm(ticketRequestForm);
            return "redirect:/tickets";
        } else {
            appUserService.addAppUserToModel(model);
            model.put("ticketRequestForm", ticketRequestForm);
            return "create_ticket";
        }
    }

    @RequestMapping(value = "/manager/ticket/{id}", method = RequestMethod.GET)
    public String reviewTicket(@PathVariable("id") int id, ModelMap model) {
        TicketResponseForm ticketResponseForm = ticketService.createTicketResponseFormFromTicketById(id);
        List<String> statuses = ticketStatusService.getAllTicketStatuses().stream().map(TicketStatus::getName).collect(Collectors.toList());
        model.put("statuses", statuses);
        model.put("ticketResponseForm", ticketResponseForm);
        return "review_ticket";
    }

    @RequestMapping(value = "/manager/ticket/{id}", method = RequestMethod.POST)
    public String reviewTicket(@PathVariable("id") int id, ModelMap model, @Valid TicketResponseForm ticketResponseForm, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            ticketService.updateTicketFromTicketResponseFormById(id, ticketResponseForm);
            return "redirect:/tickets";
        } else {
            List<String> statuses = ticketStatusService.getAllTicketStatuses().stream().map(TicketStatus::getName).collect(Collectors.toList());
            model.put("statuses", statuses);
            model.put("ticketResponseForm", ticketResponseForm);
            return "review_ticket";
        }
    }
}