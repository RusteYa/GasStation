package com.kpfu.itis.gasstation.aspects;

import com.kpfu.itis.gasstation.forms.NewsForm;
import com.kpfu.itis.gasstation.service.entities.NewsService;
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
public class LogNews {
    private static final Logger logger = LogManager.getLogger(NewsService.class);

    @Before("execution(* *..NewsService.saveNewsFromNewsForm(..))")
    public void loggingCreatedNews(JoinPoint jp) {
        String msg = "Created news with header: " + ((NewsForm) jp.getArgs()[0]).getHeader();
        logger.info(msg);
    }

    @Before("execution(* *..NewsService.deleteNewsWithId(..))")
    public void loggingDeletedNews(JoinPoint jp) {
        String msg = "Deleted news with id: " + jp.getArgs()[0];
        logger.info(msg);
    }

    @Before("execution(* *..NewsService.updateNewsFromNewsFormById(..))")
    public void loggingUpdatedNews(JoinPoint jp) {
        String msg = "Deleted news with header: " + ((NewsForm) jp.getArgs()[1]).getHeader();
        logger.info(msg);
    }
}
