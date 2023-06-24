package com.gamedoora.gateway.config;


import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;




@Configuration
public class SecurityConfig{

    @Bean
    SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        return http
                .authorizeExchange(getAuthorizeExchangeSpecCustomizer())
                .oauth2Login(withDefaults())
                .build();
    }

    private static Customizer<ServerHttpSecurity.AuthorizeExchangeSpec> getAuthorizeExchangeSpecCustomizer() {
        return exchange -> exchange
                .pathMatchers(HttpMethod.GET,
                        "/*.html","/api/health"
                ).permitAll()
                .anyExchange().authenticated();
    }



}

