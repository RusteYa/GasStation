package com.kpfu.itis.gasstation.service_test;

import com.kpfu.itis.gasstation.entities.AppRole;
import com.kpfu.itis.gasstation.entities.AppUser;
import com.kpfu.itis.gasstation.forms.RegistrationForm;
import com.kpfu.itis.gasstation.forms.UserForm;
import com.kpfu.itis.gasstation.repositories.AppUserRepository;
import com.kpfu.itis.gasstation.service.entities_service.AppUserService;
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
public class AppUserServiceTest {
    @Autowired
    private AppUserService appUserService;

    @MockBean
    private AppUserRepository appUserRepositoryMock;

    private List<AppUser> appUserList = new ArrayList<>();
    private AppUser appUser = new AppUser();

    @Before
    public void setUp() {
        appUser.setId(0);
        appUser.setLogin("qwerty");
        AppRole appRole = new AppRole();
        appRole.setName("MANAGER");
        appUser.setAppRole(appRole);
        appUserList.add(appUser);
    }

    @Test
    public void testGetAllAppUsers() {
        when(appUserRepositoryMock.findAll()).thenReturn(appUserList);

        List<AppUser> appUserList1 =  appUserService.getAllAppUsers();
        Assert.assertTrue(appUserList.containsAll(appUserList1) && appUserList1.containsAll(appUserList));
    }

    @Test
    public void testGetFirstManagerAppUser() {
        when(appUserRepositoryMock.findFirstByAppRoleName("ROLE_MANAGER")).thenReturn(appUser);

        Assert.assertEquals(appUser, appUserService.getFirstManagerAppUser());
    }

    @Test
    public void testGetAppUserByLogin() {
        when(appUserRepositoryMock.findByLogin("qwerty")).thenReturn(appUser);

        Assert.assertEquals(appUser, appUserService.getAppUserByLogin("qwerty"));
    }

    @Test
    public void testSaveAppUserFromRegistrationForm() {
        when(appUserRepositoryMock.save(any(AppUser.class))).thenReturn(appUser);

        RegistrationForm registrationForm = new RegistrationForm();
        registrationForm.setPassword("123");
        registrationForm.setLogin("qwerty");
        AppUser appUser1 = appUserService.saveAppUserFromRegistrationForm(registrationForm);
        Assert.assertEquals(registrationForm.getLogin(), appUser1.getLogin());
    }

    @Test
    public void testDeleteAppUserWithId() {
        when(appUserRepositoryMock.deleteAppUserById(0)).thenReturn(0);

        Assert.assertTrue(0 == appUserService.deleteAppUserWithId(0));
    }

    @Test
    public void testCreateUserFormFromAppUserWithId() {
        when(appUserRepositoryMock.findById(0)).thenReturn(appUser);

        Assert.assertEquals(appUser.getLogin(), appUserService.createUserFormFromAppUserWithId(0).getLogin());
    }


    @Test
    public void testUpdateAppUserFromUserFormById() {
        when(appUserRepositoryMock.findById(0)).thenReturn(appUser);
        when(appUserRepositoryMock.save(any(AppUser.class))).thenReturn(appUser);

        UserForm userForm = appUserService.createUserFormFromAppUserWithId(0);
        userForm.setLogin("qwerty1");
        Assert.assertEquals(appUser.getName(), appUserService.updateAppUserFromUserFormById(0, userForm).getName());
    }
}
