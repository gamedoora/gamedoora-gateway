package com.gamedoora.gateway.util;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

public class AuthenticationFilter implements GatewayFilter {

    private JwtUtil jwtUtil;

    private RouteValidator routeValidator;

    public RouteValidator getRouteValidator() {
        return routeValidator;
    }

    @Autowired
    public void setRouteValidator(RouteValidator routeValidator) {
        this.routeValidator = routeValidator;
    }

    public JwtUtil getJwtUtil() {
        return jwtUtil;
    }

    @Autowired
    public void setJwtUtil(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    // If the path is not allowed, check auth token, if found, extract it, and forward it to aggregator.
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        if (routeValidator.isSecured.test(request)) {
            if (isAuthMissing(request))
                return onError(exchange, "Authorization header is missing in request", HttpStatus.UNAUTHORIZED);

            final String token = getAuthHeader(request);

            if (jwtUtil.isInvalid(token))
                return onError(exchange, "Authorization header is invalid", HttpStatus.UNAUTHORIZED);

            Claims claims = jwtUtil.getAllClaimsFromToken(token);
            exchange.getRequest().mutate()
                    .header("id", String.valueOf(claims.get("id")))
                    .header("role", String.valueOf(claims.get("role")))
                    .build();

        }
        return chain.filter(exchange);
    }

    private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        return response.setComplete();
    }

    private String getAuthHeader(ServerHttpRequest request) {
        return request.getHeaders().getOrEmpty("Authorization").get(0);
    }

    private boolean isAuthMissing(ServerHttpRequest request) {
        return !request.getHeaders().containsKey("Authorization");
    }

}
