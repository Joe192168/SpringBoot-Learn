package com.joe.oauth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @program: springboot-learn
 * @description: web配置器
 * @author: xiaoqiaohui
 * @create: 2019-12-27 13:16
 **/
@Configuration
public class WebConfig implements WebMvcConfigurer {


    /**
     * 视图解析器
     * @return
     */
    /*public InternalResourceViewResolver viewResolver(){
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setSuffix(".jsp");
        viewResolver.setPrefix("/WEB-INF/view/");
        return viewResolver;
    }*/

    /**
     * 默认Url根路径跳转到/login，此url为spring security提供
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("redirect:/login-view");
        registry.addViewController("/login-view").setViewName("login");
    }
}
