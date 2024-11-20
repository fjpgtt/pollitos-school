package com.iwaconsolti.school.miguel.persistence.repository;

import com.iwaconsolti.school.miguel.persistence.model.Grade;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Repository
public interface GradeRepository extends CrudRepository<Grade, Long> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Grade g WHERE g.courseId = ?1")
    int deleteByCourseId(int courseId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Grade g WHERE g.studentId = ?1")
    int deleteByStudentId(int studentId);

    //@Query("SELECT g FROM Grade g WHERE g.studentId = ?1")
    @Query("SELECT s.firstName AS firstName, s.lastName AS lastName, s.age AS age, c.professorName AS professorName, " +
            "c.nameCourse AS nameCourse, g.score AS score " +
            " FROM Grade g " +
            " INNER JOIN Students s ON g.studentId = s.id " +
            " INNER JOIN Courses c ON g.courseId = c.id " +
            " WHERE g.studentId = ?1")
    List<Map<String,Object>> findAllGradeByStudentId(int studentId);

    Grade save(Grade grade);
}
