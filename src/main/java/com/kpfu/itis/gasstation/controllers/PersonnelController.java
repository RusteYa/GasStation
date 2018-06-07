package com.kpfu.itis.gasstation.controllers;

import com.kpfu.itis.gasstation.entities.AppRole;
import com.kpfu.itis.gasstation.entities.AppUser;
import com.kpfu.itis.gasstation.forms.UserForm;
import com.kpfu.itis.gasstation.service.entities_service.AppRoleService;
import com.kpfu.itis.gasstation.service.entities_service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Rustem.
 */
@Controller
@RequestMapping(value = "")
public class PersonnelController {
    private final AppUserService appUserService;
    private final AppRoleService appRoleService;

    @Autowired
    public PersonnelController(AppUserService appUserService, AppRoleService appRoleService) {
        this.appUserService = appUserService;
        this.appRoleService = appRoleService;
    }

    @RequestMapping(value = "/manager/personnel_management/pdf", method = RequestMethod.GET)
    public String personnelPDF() {
        return "personnelView";
    }

    @RequestMapping(value = "/manager/personnel_management", method = RequestMethod.GET)
    public String personnel(ModelMap model) {
        List<AppUser> userlist = appUserService.getAllAppUsersWithoutCurrentAppUser();
        model.put("userlist", userlist);
        return "personnel_management";
    }

    @ResponseBody
    @RequestMapping(value = "/manager/personnel_management/find", method = RequestMethod.GET)
    public ModelAndView findPersonnelContainsValue(@RequestParam(value = "value") String value, ModelMap model) {
        List<AppUser> userlist = appUserService.getAllAppUsersWithoutCurrentAppUserByLoginContains(value);
        model.put("userlist", userlist);
        return new ModelAndView(new MappingJackson2JsonView(), model);
    }

    @RequestMapping(value = "/manager/personnel_management/{id}/delete", method = RequestMethod.POST)
    @Transactional
    public String deleteAppUser(@PathVariable("id") int id) {
        appUserService.deleteAppUserWithId(id);
        return "redirect:/manager/personnel_management";
    }

    @RequestMapping(value = "/manager/user/{id}", method = RequestMethod.GET)
    public String updateAppUser(@PathVariable("id") int id, ModelMap model) {
        model.put("status", "Изменить");
        UserForm userForm = appUserService.createUserFormFromAppUserWithId(id);
        List<String> roles = appRoleService.getAllAppRoles().stream().map(AppRole::getName).collect(Collectors.toList());
        model.put("userForm", userForm);
        model.put("roles", roles);
        return "update_user";
    }

    @RequestMapping(value = "/manager/user/{id}", method = RequestMethod.POST)
    public String updateUser(@PathVariable("id") int id, ModelMap model, @Valid UserForm userForm, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            appUserService.updateAppUserFromUserFormById(id, userForm);
            return "redirect:/manager/personnel_management";
        } else {
            model.put("status", "Изменить");
            List<String> roles = appRoleService.getAllAppRoles().stream().map(AppRole::getName).collect(Collectors.toList());
            model.addAttribute("userForm", userForm);
            model.addAttribute("roles", roles);
            return "update_user";
        }
    }
}
