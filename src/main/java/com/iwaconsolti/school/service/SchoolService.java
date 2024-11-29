package com.iwaconsolti.school.service;

import com.iwaconsolti.school.model.School;

import java.util.Optional;

public interface SchoolService {

    School createSchool(School school);

    Optional<School> findByName(String name);
}
