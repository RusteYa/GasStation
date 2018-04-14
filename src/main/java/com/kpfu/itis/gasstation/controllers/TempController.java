package com.kpfu.itis.gasstation.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Rustem.
 */
@Controller
public class TempController {
    @RequestMapping(value = "/blank", method = RequestMethod.GET)
    public String blank(ModelMap model) {
        return "temp/blank";
    }

    @RequestMapping(value = "/checkout", method = RequestMethod.GET)
    public String checkout(ModelMap model) {
        return "temp/checkout";
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(ModelMap model) {
        return "temp/index";
    }

    @RequestMapping(value = "/product-page", method = RequestMethod.GET)
    public String productpage(ModelMap model) {
        return "temp/product-page";
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public String products(ModelMap model) {
        return "temp/products";
    }
}
