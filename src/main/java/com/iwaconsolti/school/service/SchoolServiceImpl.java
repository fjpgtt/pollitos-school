package com.iwaconsolti.school.service;

import com.iwaconsolti.school.model.School;
import com.iwaconsolti.school.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class SchoolServiceImpl implements SchoolService{

    private final SchoolRepository schoolRepository;

    @Autowired
    public SchoolServiceImpl(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    @Override
    public School createSchool(String schoolName) {
        if (schoolName == null || schoolName.trim().isEmpty()) {
            throw new IllegalArgumentException("School name cannot be null or empty");
        }

        Optional<School> existingSchool = schoolRepository.findByName(schoolName);
        if (existingSchool.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "School name already exists");
        }

        School school = new School();
        school.setName(schoolName);

        return schoolRepository.save(school);
    }

    @Override
    public Optional<School> findByName(String name) {
        return schoolRepository.findByName(name);
    }
}
