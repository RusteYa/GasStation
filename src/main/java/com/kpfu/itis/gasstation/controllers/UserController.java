package com.kpfu.itis.gasstation.controllers;

import com.kpfu.itis.gasstation.entities.User;
import com.kpfu.itis.gasstation.repositories.UserRepository;
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
    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getUsers(ModelMap model) {
        List<User> users = userRepository.findAll();
        model.put("users", users);
        return "views/users/userlist";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getUser(@PathVariable("id") int id, ModelMap model) {
        User user = userRepository.findById(id);
        model.put("user", user);
        return "views/users/user";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String addUser(@RequestParam String name) {
        User user = new User();
        user.setName(name);
        userRepository.save(user);
        return "redirect:users";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String changeUser(@PathVariable("id") int id, ModelMap model, @RequestParam String name) {
        User user = userRepository.findById(id);
        user.setName(name);
        userRepository.deleteUserById(id);
        userRepository.save(user);
        model.put("user", user);
        return "redirect:/users/" + id;
    }

    @RequestMapping(value = "/{id}/del", method = RequestMethod.POST)
    public String deleteUser(@PathVariable("id") int id) {
        userRepository.deleteUserById(id);
        return "redirect:users";
    }
}
