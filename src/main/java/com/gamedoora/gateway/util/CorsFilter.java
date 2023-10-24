package com.gamedoora.gateway.util;

import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.SystemUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Component
public class CorsFilter implements GatewayFilter {


    // If the path is not allowed, check auth token, if found, extract it, and forward it to aggregator.
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        exchange.getResponse().getHeaders().
                set("Access-Control-Allow-Origin", "*");
        exchange.getResponse().getHeaders().
                set("Access-Control-Allow-Credentials", "true");
        exchange.getResponse().getHeaders().
                set("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
        exchange.getResponse().getHeaders().
                set("Access-Control-Max-Age", "3600");
        exchange.getResponse().getHeaders().
                set("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, remember-me, Authorization");
        return chain.filter(exchange);
    }

}
