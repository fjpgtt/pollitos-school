package com.iwaconsolti.school.demo.repository;

import com.iwaconsolti.school.demo.model.Grade;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Integer>  {

    @Query("SELECT g FROM grades g WHERE g.student.school.id = :schoolId")
    List<Grade> findBySchoolId(@Param("schoolId") int schoolId);

    @Transactional
    @Modifying
    @Query("DELETE FROM grades g WHERE g.courseId = :courseId")
    void deleteAllByCourseId(@Param("courseId") int courseId);
}
