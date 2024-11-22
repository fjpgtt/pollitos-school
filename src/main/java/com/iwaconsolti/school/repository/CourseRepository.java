package com.iwaconsolti.school.repository;

import com.iwaconsolti.school.model.Course;
import com.iwaconsolti.school.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
    @Query("SELECT c FROM Course c WHERE c.id = :id AND c.school.id = :schoolId")
    Optional<Course> findByIdAndSchoolName(@Param("id") int id, @Param("schoolId") int schoolId);
}
