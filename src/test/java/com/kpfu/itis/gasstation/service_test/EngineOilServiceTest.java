package com.kpfu.itis.gasstation.service_test;

import com.kpfu.itis.gasstation.entities.EngineOil;
import com.kpfu.itis.gasstation.forms.EngineOilForm;
import com.kpfu.itis.gasstation.repositories.EngineOilRepository;
import com.kpfu.itis.gasstation.service.entities_service.EngineOilService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Created by Rustem.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EngineOilServiceTest {
    @Autowired
    private EngineOilService engineOilService;

    @MockBean
    private EngineOilRepository engineOilRepositoryMock;

    private List<EngineOil> engineOilsList = new ArrayList<>();
    private List<String> manafacturersList = new ArrayList<>();
    private EngineOil engineOil = new EngineOil();;

    @Before
    public void setUp() {
        engineOil.setId(0);
        engineOil.setName("oil");
        engineOil.setManafacturer("ЛУКОЙЛ");
        engineOilsList.add(engineOil);
        manafacturersList.add("ЛУКОЙЛ");
    }

    @Test
    public void testGetAllEngineOils() {
        when(engineOilRepositoryMock.findAll()).thenReturn(engineOilsList);

        List<EngineOil> engineOilList2 =  engineOilService.getAllEngineOils();
        Assert.assertTrue(engineOilsList.containsAll(engineOilList2) && engineOilList2.containsAll(engineOilsList));
    }

    @Test
    public void testGetAllEngineOilsWithManafacturer() {
        when(engineOilRepositoryMock.findAllByManafacturer(any(String.class))).thenReturn(engineOilsList);

        List<EngineOil> engineOilList2 =  engineOilService.getAllEngineOilsWithManafacturer("ЛУКОЙЛ");
        Assert.assertTrue(engineOilsList.containsAll(engineOilList2) && engineOilList2.containsAll(engineOilsList));
    }

    @Test
    public void testGetAllManafacturers() {
        when(engineOilRepositoryMock.findAllManafacturers()).thenReturn(manafacturersList);

        List<String> manafacturersList2 =  engineOilService.getAllManafacturers();
        Assert.assertTrue(manafacturersList.containsAll(manafacturersList2) && manafacturersList2.containsAll(manafacturersList));
    }

    @Test
    public void testSaveEngineOilFromEngineOilForm() {
        when(engineOilRepositoryMock.findById(0)).thenReturn(engineOil);
        when(engineOilRepositoryMock.save(any(EngineOil.class))).thenReturn(engineOil);

        EngineOilForm engineOilForm = engineOilService.createEngineOilFormFromEngineOilById(0);
        engineOilForm.setFileDatas(new MultipartFile[0]);
        EngineOil engineOil1 = engineOilService.saveEngineOilFromEngineOilForm(engineOilForm);
        Assert.assertEquals(engineOilForm.getName(), engineOil1.getName());
    }

    @Test
    public void testDeleteEngineOilWithId() {
        when(engineOilRepositoryMock.deleteEngineOilById(0)).thenReturn(0);

        Assert.assertTrue(0 == engineOilService.deleteEngineOilWithId(0));
    }

    @Test
    public void testCreateEngineOilFormFromEngineOilById() {
        when(engineOilRepositoryMock.findById(0)).thenReturn(engineOil);

        Assert.assertEquals(engineOil.getName(), engineOilService.createEngineOilFormFromEngineOilById(0).getName());
    }

    @Test
    public void testUpdateEngineOilFromEngineOilFormById() {
        when(engineOilRepositoryMock.findById(0)).thenReturn(engineOil);
        when(engineOilRepositoryMock.save(any(EngineOil.class))).thenReturn(engineOil);

        EngineOilForm engineOilForm = engineOilService.createEngineOilFormFromEngineOilById(0);
        engineOilForm.setName("name");
        engineOilForm.setFileDatas(new MultipartFile[0]);
        EngineOil engineOil1 = engineOilService.updateEngineOilFromEngineOilFormById(0, engineOilForm);
        Assert.assertEquals(engineOil.getName(), engineOil1.getName());
    }
}
