package org.wdn.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    public ModelMapper getModelMapper(){
        return new ModelMapper();
    }
    @Bean
    public ObjectMapper getObjectMapper(){
        return new ObjectMapper();
    }
}
