package com.iwaconsolti.school.demo.repository;

import com.iwaconsolti.school.demo.model.Grade;
import com.iwaconsolti.school.demo.model.Student;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    List<Student> findBySchoolId(int schoolId);

    @Transactional
    @Modifying
    @Query("DELETE FROM grades g WHERE g.studentId = :studentId")
    void deleteAllByStudentId(@Param("studentId") int studentId);

    @Query("SELECT g FROM grades g WHERE g.student.id = :studentId AND g.student.school.id = :schoolId")
    List<Grade> findGradesByStudentAndSchool(@Param("studentId") int studentId, @Param("schoolId") int schoolId);
}
