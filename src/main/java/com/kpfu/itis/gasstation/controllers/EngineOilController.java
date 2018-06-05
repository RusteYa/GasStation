package com.kpfu.itis.gasstation.controllers;

import com.kpfu.itis.gasstation.entities.EngineOil;
import com.kpfu.itis.gasstation.forms.EngineOilForm;
import com.kpfu.itis.gasstation.service.entities_service.AppUserService;
import com.kpfu.itis.gasstation.service.entities_service.EngineOilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Rustem.
 */
@Controller
public class EngineOilController {
    private final AppUserService appUserService;
    private final EngineOilService engineOilService;

    @Autowired
    public EngineOilController(AppUserService appUserService, EngineOilService engineOilService) {
        this.appUserService = appUserService;
        this.engineOilService = engineOilService;
    }

    @RequestMapping(value = "/engineoils/pdf", method = RequestMethod.GET)
    public String engineOilsPDF() {
        return "engineOilsView";
    }

    @RequestMapping(value = "/engineoils", method = RequestMethod.POST)
    public String engineoils(ModelMap model, @RequestParam String filtr) {
        appUserService.addAppUserToModel(model);
        List<EngineOil> oillist;
        if ("all".equals(filtr)) {
            oillist = engineOilService.getAllEngineOils();
        } else {
            oillist = engineOilService.getAllEngineOilsWithManafacturer(filtr);
        }
        List<String> manafacturerlist = engineOilService.getAllManafacturers();
        model.put("manafacturerlist", manafacturerlist);
        model.put("oillist", oillist);
        model.put("filtr", filtr);
        return "engineoils";
    }

    @RequestMapping(value = "/engineoils", method = RequestMethod.GET)
    public String engineoils(ModelMap model) {
        appUserService.addAppUserToModel(model);
        List<EngineOil> oillist = engineOilService.getAllEngineOils();
        List<String> manafacturerlist = engineOilService.getAllManafacturers();
        model.put("manafacturerlist", manafacturerlist);
        model.put("oillist", oillist);
        return "engineoils";
    }

    @RequestMapping(value = "/contentmanager/engineoil/add", method = RequestMethod.GET)
    public String addEngineOil(ModelMap model) {
        appUserService.addAppUserToModel(model);
        model.put("status", "Добавить");
        EngineOilForm engineOilForm = new EngineOilForm();
        model.put("engineOilForm", engineOilForm);
        return "create_update_engineoil";
    }

    @RequestMapping(value = "/contentmanager/engineoil/add", method = RequestMethod.POST)
    public String addEngineOil(@Valid EngineOilForm engineOilForm, BindingResult bindingResult, ModelMap model) {
        if (!bindingResult.hasErrors()) {
            engineOilService.saveEngineOilFromEngineOilForm(engineOilForm);
            return "redirect:/engineoils";
        } else {
            appUserService.addAppUserToModel(model);
            model.put("status", "Добавить");
            model.put("engineOilForm", engineOilForm);
            return "create_update_engineoil";
        }
    }

    @RequestMapping(value = "/contentmanager/engineoil/{id}/delete", method = RequestMethod.POST)
    @Transactional
    public String deleteEngineOil(@PathVariable("id") int id) {
        engineOilService.deleteEngineOilWithId(id);
        return "redirect:/engineoils";
    }

    @RequestMapping(value = "/contentmanager/engineoil/{id}", method = RequestMethod.GET)
    public String updateNews(@PathVariable("id") int id, ModelMap model) {
        appUserService.addAppUserToModel(model);
        model.put("status", "Изменить");
        EngineOilForm engineOilForm = engineOilService.createEngineOilFormFromEngineOilById(id);
        model.put("engineOilForm", engineOilForm);
        return "create_update_engineoil";
    }

    @RequestMapping(value = "/contentmanager/engineoil/{id}", method = RequestMethod.POST)
    public String updateNews(@PathVariable("id") int id, ModelMap model, @Valid EngineOilForm engineOilForm, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            engineOilService.updateEngineOilFromEngineOilFormById(id, engineOilForm);
            return "redirect:/engineoils";
        } else {
            appUserService.addAppUserToModel(model);
            model.put("status", "Изменить");
            model.addAttribute("engineOilForm", engineOilForm);
            return "create_update_engineoil";
        }
    }
}
