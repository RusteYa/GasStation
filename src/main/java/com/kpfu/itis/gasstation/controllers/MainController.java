package com.kpfu.itis.gasstation.controllers;

import com.kpfu.itis.gasstation.entities.Fuel;
import com.kpfu.itis.gasstation.repositories.FuelRepository;
import com.kpfu.itis.gasstation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by Rustem.
 */
@Controller
public class MainController {
    private final UserService userService;
    private final FuelRepository fuelRepository;

    @Autowired
    public MainController(UserService userService, FuelRepository fuelRepository) {
        this.userService = userService;
        this.fuelRepository = fuelRepository;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String main(ModelMap model) {
        userService.addUserToModel(model);

        List<Fuel> fuellist = fuelRepository.findAll();
        model.put("fuellist", fuellist);

        return "main";
    }
}
