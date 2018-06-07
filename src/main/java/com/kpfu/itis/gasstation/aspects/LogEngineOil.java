package com.kpfu.itis.gasstation.aspects;

import com.kpfu.itis.gasstation.forms.EngineOilForm;
import com.kpfu.itis.gasstation.service.entities_service.EngineOilService;
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
public class LogEngineOil {
    private static final Logger logger = LogManager.getLogger(EngineOilService.class);

    @Before("execution(* *..EngineOilService.saveEngineOilFromEngineOilForm(..))")
    public void loggingCreatedEngineOil(JoinPoint jp) {
        String msg = "Created engine oil with name: " + ((EngineOilForm) jp.getArgs()[0]).getName();
        logger.info(msg);
    }

    @Before("execution(* *..EngineOilService.deleteEngineOilWithId(..))")
    public void loggingDeletedEngineOil(JoinPoint jp) {
        String msg = "Deleted engine oil with id: " + jp.getArgs()[0];
        logger.info(msg);
    }

    @Before("execution(* *..EngineOilService.updateEngineOilFromEngineOilFormById(..))")
    public void loggingUpdatedAppUser(JoinPoint jp) {
        String msg = "Deleted engine oil with name: " + ((EngineOilForm) jp.getArgs()[1]).getName();
        logger.info(msg);
    }
}
