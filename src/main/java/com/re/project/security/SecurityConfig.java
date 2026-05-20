package com.re.project.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    // PASSWORD ENCODER

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http
    ) throws Exception {

        http

                // DISABLE CSRF

                .csrf(csrf -> csrf.disable())

                // ALLOW ALL

                .authorizeHttpRequests(auth -> auth

                        .anyRequest()
                        .permitAll()
                )

                // DISABLE SPRING LOGIN

                .formLogin(form -> form.disable())

                // LOGOUT

                .logout(logout -> logout

                        .logoutUrl("/logout")

                        .logoutSuccessUrl("/login")

                        .invalidateHttpSession(true)

                        .deleteCookies("JSESSIONID")
                );

        return http.build();
    }
}