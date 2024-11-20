package com.iwaconsolti.school.repository;

import com.iwaconsolti.school.model.School;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SchoolRepository extends JpaRepository<School, Integer> {
    Optional<School> findByName(String name);
}
