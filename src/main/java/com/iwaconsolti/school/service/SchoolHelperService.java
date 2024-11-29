package com.iwaconsolti.school.service;

import com.iwaconsolti.school.model.School;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class SchoolHelperService {
    private final SchoolService schoolService;

    public SchoolHelperService(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    public School findSchool(String schoolName) {
        return schoolService.findByName(schoolName)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid school name"));
    }
}
