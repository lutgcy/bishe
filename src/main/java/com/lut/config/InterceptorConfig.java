package com.lut.config;

import com.lut.interceptor.HelloInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Bean
    public HelloInterceptor injectHelloInterceptor() {
        return new HelloInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(injectHelloInterceptor())
                .addPathPatterns("/**")
//                .excludePathPatterns("/vue-element-admin/user/login")
//                .excludePathPatterns("/api/hr/login")
//                .excludePathPatterns("/api/employee/login")
//                .excludePathPatterns("/vue-element-admin/user/logout")
                .excludePathPatterns("/*/*/login")
                .excludePathPatterns("/*.*")
                .excludePathPatterns("/*/*.*");
    }
}
