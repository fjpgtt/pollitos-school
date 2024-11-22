package com.iwaconsolti.school.demo.repository;

import com.iwaconsolti.school.demo.model.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolRepository extends JpaRepository<School, Integer> {
}
