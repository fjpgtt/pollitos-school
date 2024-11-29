package com.iwaconsolti.school.miguel.config;

import com.iwaconsolti.school.miguel.model.Courses;
import com.iwaconsolti.school.miguel.model.School;
import com.iwaconsolti.school.miguel.model.Students;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Slf4j
@Profile("default")
public class SchoolConfig {
    @Bean
    @Qualifier("GerardoInstitute")
    public School gerardoInstitute(){
        return new School("GerardoInstitute");
    }

    @Bean
    @Qualifier("ZetCollege")
    public School zetCollege(){
        return new School("ZetCollege");
    }
}
