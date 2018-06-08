package com.kpfu.itis.gasstation.service.entities;

import com.kpfu.itis.gasstation.entities.Promotion;
import com.kpfu.itis.gasstation.forms.PromotionForm;
import com.kpfu.itis.gasstation.repositories.PromotionRepository;
import com.kpfu.itis.gasstation.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Rustem.
 */
@Service
public class PromotionService {
    private final PromotionRepository promotionRepository;
    private final UploadService uploadService;

    @Autowired
    public PromotionService(PromotionRepository promotionRepository, UploadService uploadService) {
        this.promotionRepository = promotionRepository;
        this.uploadService = uploadService;
    }

    public List<Promotion> getAllPromotions() {
        return promotionRepository.findAll();
    }

    public Promotion savePromotionFromPromotionForm(PromotionForm promotionForm) {
        String photoPath = uploadService.upload(promotionForm.getFileDatas());

        Promotion promotion = new Promotion();
        promotion.setHeader(promotionForm.getHeader());
        promotion.setBody(promotionForm.getBody());
        promotion.setPhotoPath(photoPath);
        promotion.setDate(new Date());

        return promotionRepository.save(promotion);
    }

    public int deletePromotionWithId(int id) {
        return promotionRepository.deletePromotionById(id);
    }

    public PromotionForm createPromotionFormFromPromotionById(int id) {
        Promotion promotion = promotionRepository.findById(id);
        PromotionForm promotionForm = new  PromotionForm();
        promotionForm.setHeader(promotion.getHeader());
        promotionForm.setBody(promotion.getBody());

        return promotionForm;
    }

    public Promotion updatePromotionFromPromotionFormById(int id, PromotionForm promotionForm) {
        Promotion promotion = promotionRepository.findById(id);
        String photoPath = uploadService.upload(promotionForm.getFileDatas());

        promotion.setHeader(promotionForm.getHeader());
        promotion.setBody(promotionForm.getBody());
        promotion.setDate(new Date());
        if (!"".equals(photoPath)) {
            promotion.setPhotoPath(photoPath);
        }

        return promotionRepository.save(promotion);
    }
}
