package com.iwaconsolti.school.demo.entity.repository;

import com.iwaconsolti.school.demo.entity.GradeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradeRepository extends JpaRepository<GradeEntity, Integer> {

}
