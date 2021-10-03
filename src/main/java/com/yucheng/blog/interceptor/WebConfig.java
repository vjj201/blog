package com.yucheng.blog.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author YuCheng
 * @date 2021/9/22 - 下午 03:56
 */

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private HandlerInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor).
                addPathPatterns("/admin/**").
                excludePathPatterns("/admin").
                excludePathPatterns("/admin/login");
    }
}
