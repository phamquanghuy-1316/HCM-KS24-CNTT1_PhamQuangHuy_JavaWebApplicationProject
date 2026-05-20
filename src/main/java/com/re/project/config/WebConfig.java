package com.re.project.config;

import com.re.project.security.AuthInterceptor;

import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Configuration;

import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig
        implements WebMvcConfigurer {

    private final AuthInterceptor
            authInterceptor;

    @Override
    public void addInterceptors(
            InterceptorRegistry registry
    ) {

        registry.addInterceptor(authInterceptor)

                .addPathPatterns(
                        "/student/**",
                        "/lecturer/**",
                        "/admin/**"
                )

                .excludePathPatterns(
                        "/login",
                        "/register",
                        "/do-login"
                );
    }
}