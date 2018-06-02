package com.kpfu.itis.gasstation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

@Configuration
public class FreeMarkerViewResolverConfig {
    @Bean(name = "viewResolver")
    public ViewResolver getViewResolver() {
        FreeMarkerViewResolver viewResolver = new FreeMarkerViewResolver();

        viewResolver.setCache(true);
        viewResolver.setSuffix(".ftl");
        viewResolver.setOrder(1);

        viewResolver.setContentType("text/html;charset=UTF-8");

        return viewResolver;
    }

    @Bean(name = "freemarkerConfig")
    public FreeMarkerConfigurer getFreemarkerConfig() {
        FreeMarkerConfigurer config = new FreeMarkerConfigurer();
        config.setTemplateLoaderPath("classpath:/templates");

        return config;
    }
}