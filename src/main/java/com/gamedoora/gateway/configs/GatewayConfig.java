//package com.gamedoora.gateway.configs;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.gateway.filter.GatewayFilter;
//import org.springframework.cloud.gateway.filter.factory.RequestHeaderToRequestUriGatewayFilterFactory;
//import org.springframework.cloud.gateway.route.RouteLocator;
//import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.server.reactive.ServerHttpRequest;
//import org.springframework.web.util.UriComponentsBuilder;
//

/*
* This is an easier approach to make a filter which takes care of token extraction and add it as bearer auth.
* For the time being it being kept as parking task, will pick it up soon
* */
//@Configuration
//public class GatewayConfig {
//
//    private RequestHeaderToRequestUriGatewayFilterFactory filterFactory;
//
//    public RequestHeaderToRequestUriGatewayFilterFactory getFilterFactory() {
//        return filterFactory;
//    }
//
//    @Autowired
//    public void setFilterFactory(RequestHeaderToRequestUriGatewayFilterFactory filterFactory) {
//        this.filterFactory = filterFactory;
//    }
//
//    @Bean
//    public RouteLocator customRouteLocator(RouteLocatorBuilder builder){
//        return builder.routes()
//                .route("auth" , r -> r.order(0)
//                        .path("/auth/from/ui")
//                        .filters(f -> f.filter((GatewayFilter) filterFactory, 0))
//                        .uri("path/to/idp"))
//                .route("user-service" , r -> r
//                        .order(1)
//                        .path("/users/**")
//                        .filters(f -> f.filter(filterFactory.apply(c -> {
//                            // Check if an "Authorization" header is present in the request
//                            ServerHttpRequest request = c.getRequest();
//                            HttpHeaders headers = request.getHeaders();
//                            if (headers.containsKey(HttpHeaders.AUTHORIZATION)) {
//                                // Modify the request URI based on the "Authorization" header value
//                                String authHeaderValue = headers.getFirst(HttpHeaders.AUTHORIZATION);
//                                return UriComponentsBuilder.fromUri(request.getURI())
//                                        .queryParam("token", extractTokenFromHeader(authHeaderValue))
//                                        .build();
//                            }
//                            return request.getURI(); // Use the original URI
//                        }),1))
//                        .uri("/path/to/user")
//                .build();
//    }
//
//    // Function to extract the token from the "Authorization" header (e.g., "Bearer <token>")
//    private String extractTokenFromHeader(String authHeaderValue) {
//        if (authHeaderValue != null && authHeaderValue.startsWith("Bearer ")) {
//            return authHeaderValue.substring(7); // Remove "Bearer " prefix
//        }
//        return null;
//    }
//
//
//}
