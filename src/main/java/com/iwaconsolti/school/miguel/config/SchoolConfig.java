package com.iwaconsolti.school.miguel.config;

import com.iwaconsolti.school.miguel.persistence.model.School;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Slf4j
public class SchoolConfig {

    @Profile("default")
    @Bean
    @Qualifier("GerardoInstitute")
    public School gerardoInstitute(){
        return new School("GerardoInstitute");
    }

    @Profile("default")
    @Bean
    @Qualifier("ZetCollege")
    public School zetCollege(){
        return new School("ZetCollege");
    }
}
