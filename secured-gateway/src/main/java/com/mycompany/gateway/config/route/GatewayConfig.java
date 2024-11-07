package com.mycompany.gateway.config.route;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                // Route for service-one with load balancing
                .route("service-one", r -> r.path("/api/v1/private/service/one/**")
                        .filters(f -> f.circuitBreaker(
                                config -> config
                                        .setName("service-one")
                                        .setFallbackUri("forward:/fallback")
                                )
                        )
                        .uri("lb://SERVICE-ONE")
                )
                
                // Route for service-two with load balancing
                .route("service-two", r -> r.path("/api/v1/private/service/two/**")
                        .filters(f -> f.circuitBreaker(
                                        config -> config
                                                .setName("service-two")
                                                .setFallbackUri("forward:/fallback")
                                )
                        )
                        .uri("lb://SERVICE-TWO")
                )

//                // Route for rbac with load balancing
//                .route("rbac", r -> r.path("/api/v1/private/service/rbac/**")
//                        .filters(f -> f.circuitBreaker(
//                                        config -> config
//                                                .setName("rbac")
//                                                .setFallbackUri("forward:/fallback")
//                                )
//                        )
//                        .uri("lb://RBAC")
//                )
                .build();
    }
}
