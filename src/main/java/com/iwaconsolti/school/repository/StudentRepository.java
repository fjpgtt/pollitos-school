package com.iwaconsolti.school.repository;

import com.iwaconsolti.school.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    List<Student> findBySchoolId(Integer schoolId);
}
