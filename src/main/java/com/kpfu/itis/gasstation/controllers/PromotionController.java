package com.kpfu.itis.gasstation.controllers;

import com.kpfu.itis.gasstation.entities.Promotion;
import com.kpfu.itis.gasstation.forms.PromotionForm;
import com.kpfu.itis.gasstation.service.entities.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Rustem.
 */
@Controller
public class PromotionController {
    private final PromotionService promotionService;

    @Autowired
    public PromotionController(PromotionService promotionService) {
        this.promotionService = promotionService;
    }

    @RequestMapping(value = "/promotions", method = RequestMethod.GET)
    public String promotions(ModelMap model) {
        List<Promotion> promoList = promotionService.getAllPromotions();
        model.put("promolist", promoList);
        return "promotions";
    }

    @RequestMapping(value = "/contentmanager/promotion/add", method = RequestMethod.GET)
    public String addPromotion(ModelMap model) {
        PromotionForm promotionForm = new PromotionForm();
        model.put("status", "Добавить");
        model.put("promotionForm", promotionForm);
        return "create_update_promotion";
    }

    @RequestMapping(value = "/contentmanager/promotion/add", method = RequestMethod.POST)
    public String addPromotion(ModelMap model, @Valid PromotionForm promotionForm, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            promotionService.savePromotionFromPromotionForm(promotionForm);
            return "redirect:/promotions";
        } else {
            model.put("status", "Добавить");
            model.put("promotionForm", promotionForm);
            return "create_update_promotion";
        }
    }

    @RequestMapping(value = "/contentmanager/promotion/{id}/delete", method = RequestMethod.POST)
    @Transactional
    public String deletePromotion(@PathVariable("id") int id) {
        promotionService.deletePromotionWithId(id);
        return "redirect:/promotions";
    }

    @RequestMapping(value = "/contentmanager/promotion/{id}", method = RequestMethod.GET)
    public String updatePromotion(@PathVariable("id") int id, ModelMap model) {
        PromotionForm promotionForm = promotionService.createPromotionFormFromPromotionById(id);
        model.put("status", "Изменить");
        model.put("promotionForm", promotionForm);
        return "create_update_promotion";
    }

    @RequestMapping(value = "/contentmanager/promotion/{id}", method = RequestMethod.POST)
    public String updatePromotion(@PathVariable("id") int id, ModelMap model, @Valid PromotionForm promotionForm, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            promotionService.updatePromotionFromPromotionFormById(id, promotionForm);
            return "redirect:/promotions";
        } else {
            model.put("status", "Изменить");
            model.put("promotionForm", promotionForm);
            return "create_update_promotion";
        }
    }
}
