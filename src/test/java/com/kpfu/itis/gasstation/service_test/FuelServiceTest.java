package com.kpfu.itis.gasstation.service_test;

import com.kpfu.itis.gasstation.entities.Fuel;
import com.kpfu.itis.gasstation.repositories.FuelRepository;
import com.kpfu.itis.gasstation.service.entities.FuelService;
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

import static org.mockito.Mockito.when;

/**
 * Created by Rustem.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FuelServiceTest {
    @Autowired
    private FuelService fuelService;

    @MockBean
    private FuelRepository fuelRepositoryMock;

    private List<Fuel> fuelList = new ArrayList<>();
    private Fuel fuel = new Fuel();

    @Before
    public void setUp() {
        fuelList.add(fuel);
    }

    @Test
    public void testGetAllFuels() {
        when(fuelRepositoryMock.findAll()).thenReturn(fuelList);

        List<Fuel> fuelList1 = fuelService.getAllFuels();
        Assert.assertTrue(fuelList.containsAll(fuelList1) && fuelList1.containsAll(fuelList));
    }
}
