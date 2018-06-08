package com.kpfu.itis.gasstation.aspects;

import com.kpfu.itis.gasstation.service.entities.AppUserService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import java.util.Arrays;

/**
 * Created by Rustem.
 */
@Aspect
@Component
public class AspectAround {
    private final AppUserService appUserService;

    @Autowired
    public AspectAround(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @Around("execution(* com.kpfu.itis.gasstation.controllers..*.*(.., org.springframework.ui.ModelMap, ..))")
    public Object addCurrentAppUserToModelAround(ProceedingJoinPoint jp) throws Throwable {
        ModelMap model = (ModelMap) Arrays.stream(jp.getArgs()).filter(x -> x instanceof ModelMap).findAny().orElse(null);
        if (model != null) appUserService.addAppUserToModel(model);
        return jp.proceed();
    }
}
