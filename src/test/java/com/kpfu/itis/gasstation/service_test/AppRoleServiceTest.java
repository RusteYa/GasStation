package com.kpfu.itis.gasstation.service_test;

import com.kpfu.itis.gasstation.entities.AppRole;
import com.kpfu.itis.gasstation.repositories.AppRoleRepository;
import com.kpfu.itis.gasstation.service.entities.AppRoleService;
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
public class AppRoleServiceTest {
    @Autowired
    private AppRoleService appRoleService;

    @MockBean
    private AppRoleRepository appRoleRepositoryMock;

    private List<AppRole> appRoleList = new ArrayList<>();
    private AppRole appRole = new AppRole();
    ;

    @Before
    public void setUp() {
        appRole.setName("ROLE_CLIENT");
        appRoleList.add(appRole);
    }

    @Test
    public void testGetAllAppRoles() {
        when(appRoleRepositoryMock.findAll()).thenReturn(appRoleList);

        List<AppRole> appRoleList1 = appRoleService.getAllAppRoles();
        Assert.assertTrue(appRoleList.containsAll(appRoleList1) && appRoleList1.containsAll(appRoleList));
    }

    @Test
    public void testGetAppRoleWithName() {
        when(appRoleRepositoryMock.findByName(any(String.class))).thenReturn(appRole);

        Assert.assertEquals(appRole, appRoleService.getAppRoleWithName("ROLE_CLIENT"));
    }

    @Test
    public void testGetClientAppRole() {
        when(appRoleRepositoryMock.findByName("ROLE_CLIENT")).thenReturn(appRole);

        Assert.assertEquals(appRole, appRoleService.getClientAppRole());
    }
}
