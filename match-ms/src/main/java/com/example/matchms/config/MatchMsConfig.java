package com.example.matchms.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MatchMsConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
