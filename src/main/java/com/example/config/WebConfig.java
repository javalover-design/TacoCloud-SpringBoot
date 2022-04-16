package com.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author lambda
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //视图MVC控制器配置 当访问/路径，则直接访问home.html页面
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/index").setViewName("home");

    }
}
