package com.gamedoora.gateway.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {
    @Autowired
    private AuthenticationFilter filter;

    String token = JwtTokenGen.generateToken();

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("user-service", r -> r
                        .path("/path/to/incoming/request")
                        .filters(f -> f.filter(filter))
                        .uri("path/to/target"))
                .build();
    }

    @Bean //this will get triggered before request reaches gateway, to add headers
    public GlobalFilter customGlobalFilter() {
        return (exchange, chain) -> {
            // Simulate adding JWT token to request headers
            exchange.getRequest().mutate()
                    .header("Authorization", token)
                    .header("username", "user123")
                    .header("password", "password123")
                    .build();

            return chain.filter(exchange);
        };
    }
}
