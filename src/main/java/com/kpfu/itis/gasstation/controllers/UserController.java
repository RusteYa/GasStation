package com.kpfu.itis.gasstation.controllers;

import com.kpfu.itis.gasstation.entities.AppUser;
import com.kpfu.itis.gasstation.repositories.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by Rustem.
 */
@Controller
@RequestMapping(value = "/users")
public class UserController {
    private AppUserRepository appUserRepository;

    @Autowired
    public void setAppUserRepository(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getUsers(ModelMap model) {
        List<AppUser> appUsers = appUserRepository.findAll();
        model.put("appUsers", appUsers);
        return "views/appUsers/userlist";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getUser(@PathVariable("id") int id, ModelMap model) {
        AppUser appUser = appUserRepository.findById(id);
        model.put("appUser", appUser);
        return "views/users/appUser";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String addUser(@RequestParam String name) {
        AppUser appUser = new AppUser();
        appUser.setName(name);
        appUserRepository.save(appUser);
        return "redirect:users";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String changeUser(@PathVariable("id") int id, ModelMap model, @RequestParam String name) {
        AppUser appUser = appUserRepository.findById(id);
        appUser.setName(name);
        appUserRepository.deleteUserById(id);
        appUserRepository.save(appUser);
        model.put("appUser", appUser);
        return "redirect:/users/" + id;
    }

    @RequestMapping(value = "/{id}/del", method = RequestMethod.POST)
    public String deleteUser(@PathVariable("id") int id) {
        appUserRepository.deleteUserById(id);
        return "redirect:users";
    }
}
