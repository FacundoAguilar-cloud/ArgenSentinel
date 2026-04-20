package com.argensentinel.common.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {

    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
