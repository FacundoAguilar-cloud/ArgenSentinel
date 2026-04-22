package com.argensentinel.gateway.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
       http
           .csrf(ServerHttpSecurity.CsrfSpec::disable)
           .authorizeExchange(exchanges -> exchanges
                   .pathMatchers("/actuator/**").permitAll()
                   .pathMatchers("/auth/**").permitAll()
                   .anyExchange().authenticated()
           )
               .oauth2ResourceServer(oauth2 -> oauth2
                       .jwt(jwt -> {})
               );
       return http.build();
    }
}
