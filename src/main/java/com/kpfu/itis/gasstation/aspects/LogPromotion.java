package com.kpfu.itis.gasstation.aspects;

import com.kpfu.itis.gasstation.forms.PromotionForm;
import com.kpfu.itis.gasstation.service.entities.PromotionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * Created by Rustem.
 */
@Aspect
@Component
public class LogPromotion {
    private static final Logger logger = LogManager.getLogger(PromotionService.class);

    @Before("execution(* *..PromotionService.savePromotionFromPromotionForm(..))")
    public void loggingCreatedPromotion(JoinPoint jp) {
        String msg = "Created promotion with header: " + ((PromotionForm) jp.getArgs()[0]).getHeader();
        logger.info(msg);
    }

    @Before("execution(* *..PromotionService.deletePromotionWithId(..))")
    public void loggingDeletedPromotion(JoinPoint jp) {
        String msg = "Deleted promotion with id: " + jp.getArgs()[0];
        logger.info(msg);
    }

    @Before("execution(* *..PromotionService.updatePromotionFromPromotionFormById(..))")
    public void loggingUpdatedPromotion(JoinPoint jp) {
        String msg = "Deleted promotion with header: " + ((PromotionForm) jp.getArgs()[1]).getHeader();
        logger.info(msg);
    }
}
