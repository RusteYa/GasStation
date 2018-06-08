package com.kpfu.itis.gasstation.service_test;

import com.kpfu.itis.gasstation.entities.Promotion;
import com.kpfu.itis.gasstation.forms.PromotionForm;
import com.kpfu.itis.gasstation.repositories.PromotionRepository;
import com.kpfu.itis.gasstation.service.entities_service.PromotionService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Created by Rustem.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PromotionServiceTest {
    @Autowired
    private PromotionService promotionService;

    @MockBean
    private PromotionRepository promotionRepositoryMock;

    private List<Promotion> promotionList = new ArrayList<>();
    private Promotion promotion = new Promotion();;

    @Before
    public void setUp() {
        promotion.setId(0);
        promotion.setHeader("header");
        promotionList.add(promotion);
    }

    @Test
    public void testGetAllPromotions() {
        when(promotionRepositoryMock.findAll()).thenReturn(promotionList);

        List<Promotion> promotionList2 =  promotionService.getAllPromotions();
        Assert.assertTrue(promotionList.containsAll(promotionList2) && promotionList2.containsAll(promotionList));
    }

    @Test
    public void testSavePromotionFromPromotionForm() {
        when(promotionRepositoryMock.findById(0)).thenReturn(promotion);
        when(promotionRepositoryMock.save(any(Promotion.class))).thenReturn(promotion);

        PromotionForm promotionForm = promotionService.createPromotionFormFromPromotionById(0);
        promotionForm.setFileDatas(new MultipartFile[0]);
        Promotion promotion1 = promotionService.savePromotionFromPromotionForm(promotionForm);
        Assert.assertEquals(promotionForm.getHeader(), promotion1.getHeader());
    }

    @Test
    public void testDeletePromotionWithId() {
        when(promotionRepositoryMock.deletePromotionById(0)).thenReturn(0);

        Assert.assertTrue(0 == promotionService.deletePromotionWithId(0));
    }

    @Test
    public void testCreatePromotionFormFromPromotionById() {
        when(promotionRepositoryMock.findById(0)).thenReturn(promotion);

        Assert.assertEquals(promotion.getHeader(), promotionService.createPromotionFormFromPromotionById(0).getHeader());
    }

    @Test
    public void testUpdatePromotionFromPromotionFormById() {
        when(promotionRepositoryMock.findById(0)).thenReturn(promotion);
        when(promotionRepositoryMock.save(any(Promotion.class))).thenReturn(promotion);

        PromotionForm promotionForm = promotionService.createPromotionFormFromPromotionById(0);
        promotionForm.setHeader("promotion_header");
        promotionForm.setFileDatas(new MultipartFile[0]);
        Promotion promotion1 = promotionService.updatePromotionFromPromotionFormById(0, promotionForm);
        Assert.assertEquals(promotion.getHeader(), promotion1.getHeader());
    }
}
