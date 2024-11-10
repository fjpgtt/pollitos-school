package com.iwaconsolti.school.miguel.config;

import com.iwaconsolti.school.miguel.model.School;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SchoolConfig {

    @Bean
    public School gerardoInstitute(){
        return new School("GerardoInstitute");
    }

    @Bean
    public School zetCollege(){
        return new School("ZetCollege");
    }
}
