package com.vipmin.config;

import com.vipmin.config.filter.LoginHandlerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class FilterConfig {
    @Bean //将组件注册在容器
    public WebMvcConfigurerAdapter webMvcConfigurerAdapter(){
        WebMvcConfigurerAdapter adapter = new WebMvcConfigurerAdapter() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/index.html").setViewName("login");
                registry.addViewController("/main.html").setViewName("dashboard");
            }

            //注册拦截器
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(new LoginHandlerInterceptor())
                        .addPathPatterns("/**")
                        .excludePathPatterns("/api/admin/login")
                        .excludePathPatterns("/api/user/login")
                        .excludePathPatterns("/swagger-ui.html")
                        .excludePathPatterns("/webjars/**")
                        .excludePathPatterns("/swagger-resources/**")
                        .excludePathPatterns("/api/user/checkToken")
                        .excludePathPatterns("/swagger-ui.html/swagger-resources/configuration/ui");
            }
        };
        return adapter;
    }
}
