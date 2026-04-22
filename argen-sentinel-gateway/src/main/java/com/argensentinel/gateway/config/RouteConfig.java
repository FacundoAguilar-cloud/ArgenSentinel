package com.argensentinel.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Configuration;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouteConfig {

    public RouteLocator customRoutes(RouteLocatorBuilder builder){
        return builder.routes()
                .route("api-service", r -> r
                        .path("/api/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri("http://argensentinel-api:8080")
                )


                .route("ingestion-service", r -> r
                .path("/ingestion/**")
                .filters(f -> f.stripPrefix(1))
                .uri("http://argensentinel-ingestion:8081")
                )
                .build();
    }

}
