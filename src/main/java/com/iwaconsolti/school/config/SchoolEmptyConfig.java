package com.iwaconsolti.school.config;

import com.iwaconsolti.school.model.School;
import com.iwaconsolti.school.model.schools.GerardoInstitute;
import com.iwaconsolti.school.model.schools.ZetCollege;
import com.iwaconsolti.school.service.SchoolService;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("default")
@Slf4j
@Configuration
public class SchoolEmptyConfig {

    private final SchoolService schoolService;

    @Autowired
    public SchoolEmptyConfig(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @PostConstruct
    public void populateData() {
        School gerardoInstitute = schoolService.createSchool(new GerardoInstitute("GerardoInstitute"));
        School zetCollege = schoolService.createSchool(new ZetCollege("ZetCollege"));
    }
}
