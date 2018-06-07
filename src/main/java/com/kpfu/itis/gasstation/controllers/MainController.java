package com.kpfu.itis.gasstation.controllers;

import com.kpfu.itis.gasstation.entities.Fuel;
import com.kpfu.itis.gasstation.service.entities_service.FuelService;
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
    private final FuelService fuelService;

    @Autowired
    public MainController(FuelService fuelService) {
        this.fuelService = fuelService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String main(ModelMap model) {
        List<Fuel> fuellist = fuelService.getAllFuels();
        model.put("fuellist", fuellist);
        return "main";
    }
}
