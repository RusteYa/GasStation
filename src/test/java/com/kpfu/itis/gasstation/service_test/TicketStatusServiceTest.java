package com.kpfu.itis.gasstation.service_test;

import com.kpfu.itis.gasstation.entities.TicketStatus;
import com.kpfu.itis.gasstation.repositories.TicketStatusRepository;
import com.kpfu.itis.gasstation.service.entities.TicketStatusService;
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
public class TicketStatusServiceTest {
    @Autowired
    private TicketStatusService ticketStatusService;

    @MockBean
    private TicketStatusRepository ticketStatusRepositoryMock;

    private List<TicketStatus> ticketStatusList = new ArrayList<>();
    private TicketStatus ticketStatus = new TicketStatus();
    ;

    @Before
    public void setUp() {
        ticketStatus.setName("NEW");
        ticketStatusList.add(ticketStatus);
    }

    @Test
    public void testGetAllTicketStatuses() {
        when(ticketStatusRepositoryMock.findAll()).thenReturn(ticketStatusList);

        List<TicketStatus> ticketStatusList1 = ticketStatusService.getAllTicketStatuses();
        Assert.assertTrue(ticketStatusList.containsAll(ticketStatusList1) && ticketStatusList1.containsAll(ticketStatusList));
    }

    @Test
    public void testGetTicketStatusWithName() {
        when(ticketStatusRepositoryMock.findByName(any(String.class))).thenReturn(ticketStatus);

        Assert.assertEquals(ticketStatus, ticketStatusService.getTicketStatusWithName("NEW"));
    }

    @Test
    public void testGetNewtTicketStatus() {
        when(ticketStatusRepositoryMock.findByName("NEW")).thenReturn(ticketStatus);

        Assert.assertEquals(ticketStatus, ticketStatusService.getNewTicketStatus());
    }
}
