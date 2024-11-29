package com.iwaconsolti.school.service;

import com.iwaconsolti.school.model.School;
import com.iwaconsolti.school.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SchoolServiceImpl implements SchoolService{

    private final SchoolRepository schoolRepository;

    @Autowired
    public SchoolServiceImpl(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    @Override
    public School createSchool(School school) {
        return schoolRepository.save(school);
    }

    @Override
    public Optional<School> findByName(String name) {
        return schoolRepository.findByName(name);
    }
}
