package com.coffeemanager.coffeemanager.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .httpBasic(httpBasic -> {})
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/api/roles/**",
                                "/api/permisos/**",
                                 "/api/roles-permisos/**",
                                "/api/usuarios/**",
                                "/api/auth/**"
                        ).permitAll()
                        .anyRequest().authenticated()
                );

        return http.build();
    }
}