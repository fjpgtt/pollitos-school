package com.iwaconsolti.school.config;


import com.iwaconsolti.school.model.Schools.GerardoInstitute;
import com.iwaconsolti.school.model.School;
import com.iwaconsolti.school.model.Schools.ZetCollege;
import com.iwaconsolti.school.service.SchoolService;
import com.iwaconsolti.school.service.SchoolServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("empty")
@Configuration
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
    public SchoolService gerardoServiceDefault() {
        return new SchoolServiceImpl(gerardoInstitute());
    }

    @Bean("zetService")
    public SchoolService zetServiceDefault() {
        return new SchoolServiceImpl(zetCollege());
    }
}
