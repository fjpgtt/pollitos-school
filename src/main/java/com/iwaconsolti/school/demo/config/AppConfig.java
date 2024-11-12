package com.iwaconsolti.school.demo.config;

import com.iwaconsolti.school.demo.model.School;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class AppConfig {

    @Bean
    public School gerardoInstitute(){
        return new School("GerardoInstitute");
    }

    @Profile("test")
    public School zetCollege(){
        return new School("ZetCollege");
    }
}
