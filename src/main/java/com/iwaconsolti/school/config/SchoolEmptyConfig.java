package com.iwaconsolti.school.config;


import com.iwaconsolti.school.model.schools.GerardoInstitute;
import com.iwaconsolti.school.model.School;
import com.iwaconsolti.school.model.schools.ZetCollege;
import com.iwaconsolti.school.service.SchoolService;
import com.iwaconsolti.school.service.SchoolServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("empty")
@Configuration
@Slf4j
public class SchoolEmptyConfig {

    @Bean
    public School gerardoInstitute() {
        return new GerardoInstitute();
    }

    @Bean
    public School zetCollege() {
        return new ZetCollege();
    }

    @Bean("gerardoService")
    public SchoolService gerardoService(@Value("${school.score.limit:100}") int limitGrade) {
        log.info("gerardoServiceEmpty initialized");
        return new SchoolServiceImpl(gerardoInstitute(), limitGrade);
    }

    @Bean("zetService")
    public SchoolService zetService(@Value("${school.score.limit:100}") int limitGrade) {
        log.info("zetServiceEmpty initialized");
        return new SchoolServiceImpl(new ZetCollege(), limitGrade);
    }
}
