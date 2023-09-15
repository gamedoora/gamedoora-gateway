package com.gamedoora.gateway.config;

import com.gamedoora.gateway.util.AuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
    @Value("${proxyHost}")
    private String proxyHost;

    private AuthenticationFilter authenticationFilter;

    public AuthenticationFilter getAuthenticationFilter() {
        return authenticationFilter;
    }

    @Autowired
    public void setAuthenticationFilter(AuthenticationFilter authenticationFilter) {
        this.authenticationFilter = authenticationFilter;
    }

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes() // we don't need form data, if the path that being asked is part of gated path, then look at header, it true
                .route("proxy-service", r -> r
                        .path("/aggregate/**")
                        .filters(f -> f.filter(authenticationFilter))
                        .uri(proxyHost))
                .build();
    }


    /*
    * I am currently assuming that we are receiving proxy Url and all we need to check is whether it
    * is in the secured list of API or not, if not, check for auth and add it in the URl and send it to
    * aggregator proxy and then aggregator will manage the service discovery and request.
    *
    * AuthenticationFilter checks whether bearer token is there or not and updates it into the URI as header
    * from claims of JWT token.
    *
    * The below commented part helps in routing it to particular path, let's assume aggregator has a fixed
    * path for some service, let's assume user-service then we can make a conditional check whether this is the
    * correct route or else check the next one, in case of multiple routes.
    *
    .route("proxy-path", r -> r
            .predicate(request -> customPredicate("user-service" , request))// these paths will come from properties
            .filters(f -> f.filter(authenticationFilter))
            .uri("/path/to/aggregator"))

    private boolean customPredicate(String path , ServerWebExchange exchange){
        String uri = exchange.getRequest().getURI().toString();
        return uri.contains(path);
    }*/
}
