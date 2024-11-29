package com.iwaconsolti.school.repository;

import com.iwaconsolti.school.model.Grade;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Integer> {

    @Query("SELECT g FROM Grade g WHERE g.student.id = :StudentId AND g.school.id = :schoolId")
    List<Grade> findByStudentIdAndSchool(@Param("StudentId") int studentId, @Param("schoolId") int schoolId);

    @Query("SELECT g FROM Grade g WHERE g.course.id = :courseId AND g.school.id = :schoolId")
    List<Grade> findByCourseIdAndSchool(@Param("courseId") int courseId, @Param("schoolId") int schoolId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Grade g WHERE g.student.id = :studentId AND g.school.id = :schoolId")
    void deleteByStudentIdAndSchoolId(int studentId, int schoolId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Grade g WHERE g.course.id = :courseId AND g.school.id = :schoolId")
    void deleteByCourseIdAndSchoolId(int courseId, int schoolId);
}
