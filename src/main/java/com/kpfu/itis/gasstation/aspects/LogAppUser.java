package com.kpfu.itis.gasstation.aspects;

import com.kpfu.itis.gasstation.forms.RegistrationForm;
import com.kpfu.itis.gasstation.forms.UserForm;
import com.kpfu.itis.gasstation.service.entities.AppUserService;
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
public class LogAppUser {
    private static final Logger logger = LogManager.getLogger(AppUserService.class);

    @Before("execution(* *..AppUserService.saveAppUserFromRegistrationForm(..))")
    public void loggingCreatedAppUser(JoinPoint jp) {
        String msg = "Created user with login: " + ((RegistrationForm) jp.getArgs()[0]).getLogin();
        logger.info(msg);
    }

    @Before("execution(* *..UserDetailsServiceImpl.loadUserByUsername(..))")
    public void loggingAuthorizatedAppUser(JoinPoint jp) {
        String msg = "Authorizated user with login: " + jp.getArgs()[0];
        logger.info(msg);
    }

    @Before("execution(* *..AppUserService.deleteAppUserWithId(..))")
    public void loggingDeletedAppUser(JoinPoint jp) {
        String msg = "Deleted user with id: " + jp.getArgs()[0];
        logger.info(msg);
    }

    @Before("execution(* *..AppUserService.updateAppUserFromUserFormById(..))")
    public void loggingUpdatedAppUser(JoinPoint jp) {
        String msg = "Updated user with login: " + ((UserForm) jp.getArgs()[1]).getLogin();
        logger.info(msg);
    }
}
