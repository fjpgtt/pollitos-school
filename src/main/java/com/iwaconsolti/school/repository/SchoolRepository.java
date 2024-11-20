package com.iwaconsolti.school.repository;

import com.iwaconsolti.school.model.School;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolRepository extends JpaRepository<School, Integer> {
}
