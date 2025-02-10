package com.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ApplicationConfiguration {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeHttpRequests()
            .requestMatchers(HttpMethod.POST, "/api/clients").permitAll()
            .requestMatchers(HttpMethod.GET, "/api/clients/**").permitAll()
            .requestMatchers(HttpMethod.PUT, "/api/clients/**").permitAll()
            .requestMatchers(HttpMethod.DELETE, "/api/clients/**").permitAll()
            .requestMatchers(HttpMethod.POST, "/api/claims").permitAll()
            .requestMatchers(HttpMethod.GET, "/api/claims/**").permitAll()
            .requestMatchers(HttpMethod.PUT, "/api/claims/**").permitAll()
            .requestMatchers(HttpMethod.DELETE, "/api/claims/**").permitAll()
            .requestMatchers(HttpMethod.POST, "/api/policies").permitAll()
            .requestMatchers(HttpMethod.GET, "/api/policies/**").permitAll()
            .requestMatchers(HttpMethod.PUT, "/api/policies/**").permitAll()
            .requestMatchers(HttpMethod.DELETE, "/api/policies/**").permitAll()
            .anyRequest().permitAll() // Allow all other requests without authentication
            .and()
            .formLogin().disable() // Disable form login
            .httpBasic().disable(); // Disable basic authentication
        
        return http.build();
    }
}