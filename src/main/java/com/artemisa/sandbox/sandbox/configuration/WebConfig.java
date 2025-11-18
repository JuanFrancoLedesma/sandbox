package com.artemisa.sandbox.sandbox.configuration;

import com.artemisa.sandbox.sandbox.interceptors.MiInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final MiInterceptor miInterceptor;

    public WebConfig(MiInterceptor miInterceptor){
        this.miInterceptor = miInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(miInterceptor)
                .addPathPatterns("/**");
    }
}
