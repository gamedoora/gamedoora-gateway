package com.gamedoora.gateway.config;


import org.springframework.context.annotation.Bean;


import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;


import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;



@EnableWebSecurity
public class SecurityConfig{

    @Bean
    public SecurityFilterChain  filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(Customizer.withDefaults())
                .oauth2Login(Customizer.withDefaults());
        return http.build();
    }

}

