package com.kpfu.itis.gasstation.controllers;

import com.kpfu.itis.gasstation.entities.Fuel;
import com.kpfu.itis.gasstation.service.entities_service.FuelService;
import com.kpfu.itis.gasstation.service.entities_service.AppUserService;
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
    private final AppUserService appUserService;
    private final FuelService fuelService;

    @Autowired
    public MainController(AppUserService appUserService, FuelService fuelService) {
        this.appUserService = appUserService;
        this.fuelService = fuelService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String main(ModelMap model) {
        appUserService.addAppUserToModel(model);

        List<Fuel> fuellist = fuelService.getAllFuels();
        model.put("fuellist", fuellist);

        return "main";
    }
}
