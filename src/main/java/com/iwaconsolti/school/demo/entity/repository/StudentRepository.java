package com.iwaconsolti.school.demo.entity.repository;

import com.iwaconsolti.school.demo.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<StudentEntity, Integer> {

}
