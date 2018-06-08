package com.kpfu.itis.gasstation.service_test;

import com.kpfu.itis.gasstation.entities.AppUser;
import com.kpfu.itis.gasstation.service.UserDetailsServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Rustem.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDetailsServiceImplTest {
    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Test
    public void testGetAllFuels() {
        String login = "manager";
        AppUser appUser = (AppUser) userDetailsServiceImpl.loadUserByUsername(login);
        Assert.assertEquals(login, appUser.getLogin());
    }
}
