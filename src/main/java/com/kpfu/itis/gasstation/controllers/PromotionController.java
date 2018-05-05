package com.kpfu.itis.gasstation.controllers;

import com.kpfu.itis.gasstation.entities.Promotion;
import com.kpfu.itis.gasstation.forms.PromotionForm;
import com.kpfu.itis.gasstation.repositories.PromotionRepository;
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

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * Created by Rustem.
 */
@Controller
public class PromotionController {
    private final PromotionRepository promotionRepository;
    private final UserService userService;
    private final UploadService uploadService;

    @Autowired
    public PromotionController(PromotionRepository promotionRepository, UserService userService, UploadService uploadService) {
        this.promotionRepository = promotionRepository;
        this.userService = userService;
        this.uploadService = uploadService;
    }

    @RequestMapping(value = "/promotions", method = RequestMethod.GET)
    public String promotions(ModelMap model) {
        userService.addUserToModel(model);
        List<Promotion> promoList = promotionRepository.findAll();
        model.put("promolist", promoList);
        return "promotions";
    }

    @RequestMapping(value = "/contentmanager/promotion/add", method = RequestMethod.GET)
    public String addPromotion(ModelMap model) {
        userService.addUserToModel(model);

        model.put("status", "Добавление акции");

        PromotionForm promotionForm = new PromotionForm();
        model.put("promotionForm", promotionForm);
        return "create_update_promotion";
    }

    @RequestMapping(value = "/contentmanager/promotion/add", method = RequestMethod.POST)
    public String addPromotion(ModelMap model, @Valid PromotionForm promotionForm, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            String photoPath = uploadService.upload(promotionForm.getFileDatas());

            Promotion promotion = new Promotion();
            promotion.setHeader(promotionForm.getHeader());
            promotion.setBody(promotionForm.getBody());
            promotion.setPhotoPath(photoPath);
            promotion.setDate(new Date());

            promotionRepository.save(promotion);

            return "redirect:/promotions";
        } else {
            userService.addUserToModel(model);

            model.put("status", "Добавление акции");

            model.put("promotionForm", promotionForm);
            return "create_update_promotion";
        }
    }

    @RequestMapping(value = "/contentmanager/promotion/{id}/delete", method = RequestMethod.POST)
    @Transactional
    public String deletePromotion(@PathVariable("id") int id) {
        promotionRepository.deletePromotionById(id);
        return "redirect:/promotions";
    }

    @RequestMapping(value = "/contentmanager/promotion/{id}", method = RequestMethod.GET)
    public String updatePromotion(@PathVariable("id") int id, ModelMap model) {
        userService.addUserToModel(model);

        model.put("status", "Изменение акции");

        Promotion promotion = promotionRepository.findById(id);
        PromotionForm promotionForm = new  PromotionForm();
        promotionForm.setHeader(promotion.getHeader());
        promotionForm.setBody(promotion.getBody());
        model.put("promotionForm", promotionForm);
        return "create_update_promotion";
    }

    @RequestMapping(value = "/contentmanager/promotion/{id}", method = RequestMethod.POST)
    public String updatePromotion(@PathVariable("id") int id, ModelMap model, @Valid PromotionForm promotionForm, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            Promotion promotion = promotionRepository.findById(id);
            String photoPath = uploadService.upload(promotionForm.getFileDatas());

            promotion.setHeader(promotionForm.getHeader());
            promotion.setBody(promotionForm.getBody());
            promotion.setDate(new Date());
            if (!"".equals(photoPath)) {
                promotion.setPhotoPath(photoPath);
            }

            promotionRepository.save(promotion);

            return "redirect:/promotions";
        } else {
            userService.addUserToModel(model);

            model.put("status", "Изменение акции");

            model.addAttribute("promotionForm", promotionForm);
            return "create_update_promotion";
        }
    }
}
