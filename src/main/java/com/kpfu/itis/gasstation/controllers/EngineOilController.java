package com.kpfu.itis.gasstation.controllers;

import com.kpfu.itis.gasstation.entities.EngineOil;
import com.kpfu.itis.gasstation.forms.EngineOilForm;
import com.kpfu.itis.gasstation.repositories.EngineOilRepository;
import com.kpfu.itis.gasstation.service.UploadService;
import com.kpfu.itis.gasstation.service.UserService;
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
    private final UserService userService;
    private final UploadService uploadService;
    private final EngineOilRepository engineOilRepository;

    @Autowired
    public EngineOilController(UserService userService, UploadService uploadService, EngineOilRepository engineOilRepository) {
        this.userService = userService;
        this.uploadService = uploadService;
        this.engineOilRepository = engineOilRepository;
    }

    @RequestMapping(value = "/engineoils", method = RequestMethod.POST)
    public String engineoils(ModelMap model, @RequestParam String filtr) {
        userService.addUserToModel(model);
        List<EngineOil> oillist;

        if ("all".equals(filtr)) {
            oillist = engineOilRepository.findAll();
        } else {
            oillist = engineOilRepository.findAllByManafacturer(filtr);
        }

        List<String> manafacturerlist = engineOilRepository.findAllManafacturers();

        model.put("manafacturerlist", manafacturerlist);
        model.put("oillist", oillist);
        model.put("filtr", filtr);
        return "engineoils";
    }

    @RequestMapping(value = "/engineoils", method = RequestMethod.GET)
    public String engineoils(ModelMap model) {
        userService.addUserToModel(model);

        List<EngineOil> oillist = engineOilRepository.findAll();
        List<String> manafacturerlist = engineOilRepository.findAllManafacturers();

        model.put("manafacturerlist", manafacturerlist);
        model.put("oillist", oillist);
        return "engineoils";
    }

    @RequestMapping(value = "/contentmanager/engineoil/add", method = RequestMethod.GET)
    public String addEngineOil(ModelMap model) {
        userService.addUserToModel(model);

        model.put("status", "Добавить");

        EngineOilForm engineOilForm = new EngineOilForm();
        model.put("engineOilForm", engineOilForm);
        return "create_update_engineoil";
    }

    @RequestMapping(value = "/contentmanager/engineoil/add", method = RequestMethod.POST)
    public String addEngineOil(@Valid EngineOilForm engineOilForm, BindingResult bindingResult, ModelMap model) {
        if (!bindingResult.hasErrors()) {
            String photoPath = uploadService.upload(engineOilForm.getFileDatas());

            EngineOil engineOil = new EngineOil();
            engineOil.setName(engineOilForm.getName());
            engineOil.setPrice(engineOilForm.getPrice());
            engineOil.setManafacturer(engineOilForm.getManafacturer());
            engineOil.setPhotoPath(photoPath);

            engineOilRepository.save(engineOil);

            return "redirect:/engineoils";
        } else {
            userService.addUserToModel(model);

            model.put("status", "Добавить");

            model.put("engineOilForm", engineOilForm);
            return "create_update_engineoil";
        }
    }

    @RequestMapping(value = "/contentmanager/engineoil/{id}/delete", method = RequestMethod.POST)
    @Transactional
    public String deleteEngineOil(@PathVariable("id") int id) {
        engineOilRepository.deleteEngineOilById(id);
        return "redirect:/engineoils";
    }

    @RequestMapping(value = "/contentmanager/engineoil/{id}", method = RequestMethod.GET)
    public String updateNews(@PathVariable("id") int id, ModelMap model) {
        userService.addUserToModel(model);

        model.put("status", "Изменить");

        EngineOil engineOil = engineOilRepository.findById(id);
        EngineOilForm engineOilForm = new EngineOilForm();
        engineOilForm.setName(engineOil.getName());
        engineOilForm.setPrice(engineOil.getPrice());
        engineOilForm.setManafacturer(engineOil.getManafacturer());
        model.put("engineOilForm", engineOilForm);
        return "create_update_engineoil";
    }

    @RequestMapping(value = "/contentmanager/engineoil/{id}", method = RequestMethod.POST)
    public String updateNews(@PathVariable("id") int id, ModelMap model, @Valid EngineOilForm engineOilForm, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            EngineOil engineOil = engineOilRepository.findById(id);
            String photoPath = uploadService.upload(engineOilForm.getFileDatas());

            engineOil.setName(engineOilForm.getName());
            engineOil.setPrice(engineOilForm.getPrice());
            engineOil.setManafacturer(engineOilForm.getManafacturer());
            if (!"".equals(photoPath)) {
                engineOil.setPhotoPath(photoPath);
            }

            engineOilRepository.save(engineOil);

            return "redirect:/engineoils";
        } else {
            userService.addUserToModel(model);

            model.put("status", "Изменить");

            model.addAttribute("engineOilForm", engineOilForm);
            return "create_update_engineoil";
        }
    }
}
