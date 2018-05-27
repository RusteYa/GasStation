package com.kpfu.itis.gasstation.controllers;

import com.kpfu.itis.gasstation.entities.AppRole;
import com.kpfu.itis.gasstation.entities.AppUser;
import com.kpfu.itis.gasstation.forms.UserForm;
import com.kpfu.itis.gasstation.repositories.AppRoleRepository;
import com.kpfu.itis.gasstation.repositories.AppUserRepository;
import com.kpfu.itis.gasstation.service.UserService;
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
    private final AppUserRepository appUserRepository;
    private final UserService userService;
    private final AppRoleRepository appRoleRepository;

    @Autowired
    public PersonnelController(UserService userService, AppUserRepository appUserRepository, AppRoleRepository appRoleRepository) {
        this.userService = userService;
        this.appUserRepository = appUserRepository;
        this.appRoleRepository = appRoleRepository;
    }

    @RequestMapping(value = "/manager/personnel_management", method = RequestMethod.GET)
    public String personnel(ModelMap model) {
        userService.addUserToModel(model);
        List<AppUser> userlist = appUserRepository.findAll();

        AppUser currentUser = userService.getCurrentUser();
        for (int i = 0; i < userlist.size(); i++) {
            AppUser user = userlist.get(i);
            if (user.getId() == currentUser.getId()) {
                userlist.remove(i);
                break;
            }
        }

        model.put("userlist", userlist);
        return "personnel_management";
    }

    @ResponseBody
    @RequestMapping(value = "/manager/personnel_management/find", method = RequestMethod.GET)
    public ModelAndView findPersonnelContainsValue(@RequestParam(value = "value") String value) {
        ModelMap model = new ModelMap();
        userService.addUserToModel(model);

        List<AppUser> userlist = appUserRepository.findAllByLoginContains(value);

        AppUser currentUser = userService.getCurrentUser();
        for (int i = 0; i < userlist.size(); i++) {
            AppUser user = userlist.get(i);
            if (user.getId() == currentUser.getId()) {
                userlist.remove(i);
                break;
            }
        }

        model.put("userlist", userlist);
        ModelAndView modelAndView = new ModelAndView(new MappingJackson2JsonView());
        modelAndView.addObject("userlist", userlist);

        return modelAndView;
    }

    @RequestMapping(value = "/manager/personnel_management/{id}/delete", method = RequestMethod.POST)
    @Transactional
    public String deleteNews(@PathVariable("id") int id) {
        appUserRepository.deleteUserById(id);
        return "redirect:/manager/personnel_management";
    }

    @RequestMapping(value = "/manager/user/{id}", method = RequestMethod.GET)
    public String updateUser(@PathVariable("id") int id, ModelMap model) {
        userService.addUserToModel(model);

        model.put("status", "Изменить");

        AppUser appUser = appUserRepository.findById(id);

        UserForm userForm = new UserForm();
        userForm.setName(appUser.getName());
        userForm.setLogin(appUser.getLogin());
        userForm.setEmail(appUser.getEmail());
        userForm.setRole(appUser.getAppRole().getName());

        List<String> roles = appRoleRepository.findAll().stream().map(AppRole::getName).collect(Collectors.toList());

        model.put("userForm", userForm);
        model.put("roles", roles);
        return "update_user";
    }

    @RequestMapping(value = "/manager/user/{id}", method = RequestMethod.POST)
    public String updateUser(@PathVariable("id") int id, ModelMap model, @Valid UserForm userForm, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            AppUser appUser = appUserRepository.findById(id);

            appUser.setName(userForm.getName());
            appUser.setLogin(userForm.getLogin());
            appUser.setEmail(userForm.getEmail());
            appUser.setAppRole(appRoleRepository.findByName(userForm.getRole()));

            appUserRepository.save(appUser);

            return "redirect:/manager/personnel_management";
        } else {
            userService.addUserToModel(model);

            model.put("status", "Изменить");

            List<String> roles = appRoleRepository.findAll().stream().map(AppRole::getName).collect(Collectors.toList());

            model.addAttribute("userForm", userForm);
            model.addAttribute("roles", roles);
            return "update_user";
        }
    }
}
