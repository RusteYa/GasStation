package com.kpfu.itis.gasstation.service_test;

import com.kpfu.itis.gasstation.entities.AppUser;
import com.kpfu.itis.gasstation.entities.Ticket;
import com.kpfu.itis.gasstation.entities.TicketStatus;
import com.kpfu.itis.gasstation.forms.TicketRequestForm;
import com.kpfu.itis.gasstation.forms.TicketResponseForm;
import com.kpfu.itis.gasstation.repositories.TicketRepository;
import com.kpfu.itis.gasstation.service.entities_service.AppUserService;
import com.kpfu.itis.gasstation.service.entities_service.TicketService;
import com.kpfu.itis.gasstation.service.entities_service.TicketStatusService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Created by Rustem.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TicketServiceTest {
    @Autowired
    private TicketService ticketService;

    @MockBean
    private TicketRepository ticketRepositoryMock;
    @MockBean
    private AppUserService appUserServiceMock;
    @MockBean
    private TicketStatusService ticketStatusServiceMock;

    private List<Ticket> ticketList = new ArrayList<>();
    private Ticket ticket = new Ticket();;

    @Before
    public void setUp() {
        ticket.setId(0);
        ticket.setHeader("header");
        ticket.setResponse("response");
        ticket.setTicketStatus(new TicketStatus());
        ticket.setTicketStaff(new AppUser());
        ticket.setTicketClient(new AppUser());
        ticketList.add(ticket);
    }

    @Test
    public void testGetAllTickets() {
        when(ticketRepositoryMock.findAll()).thenReturn(ticketList);

        List<Ticket> ticketList2 =  ticketService.getAllTickets();
        Assert.assertTrue(ticketList.containsAll(ticketList2) && ticketList2.containsAll(ticketList));
    }

    @Test
    public void testSaveTicketFromTicketRequestForm() {
        when(ticketRepositoryMock.save(any(Ticket.class))).thenReturn(ticket);
        when(ticketStatusServiceMock.getNewTicketStatus()).thenReturn(new TicketStatus());
        when(appUserServiceMock.getCurrentAppUser()).thenReturn(new AppUser());
        when(appUserServiceMock.getFirstManagerAppUser()).thenReturn(new AppUser());

        TicketRequestForm ticketForm = new TicketRequestForm();
        ticketForm.setHeader("header");

        Ticket ticket1 = ticketService.saveTicketFromTicketRequestForm(ticketForm);
        Assert.assertEquals(ticketForm.getHeader(), ticket1.getHeader());
    }

    @Test
    public void testCreateTicketResponseFormFromTicketById() {
        when(ticketRepositoryMock.findById(0)).thenReturn(ticket);

        Assert.assertEquals(ticket.getResponse(), ticketService.createTicketResponseFormFromTicketById(0).getResponse());
    }

    @Test
    public void testUpdateTicketFromTicketResponseFormById() {
        when(ticketRepositoryMock.findById(0)).thenReturn(ticket);
        when(ticketRepositoryMock.save(any(Ticket.class))).thenReturn(ticket);

        TicketResponseForm ticketForm = ticketService.createTicketResponseFormFromTicketById(0);
        ticketForm.setResponse("ticket_response");
        Ticket ticket1 = ticketService.updateTicketFromTicketResponseFormById(0, ticketForm);
        Assert.assertEquals(ticket.getHeader(), ticket1.getHeader());
    }
}
