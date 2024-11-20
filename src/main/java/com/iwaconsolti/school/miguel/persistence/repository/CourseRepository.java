package com.iwaconsolti.school.miguel.persistence.repository;

import com.iwaconsolti.school.miguel.persistence.model.Courses;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Repository
public interface CourseRepository extends CrudRepository<Courses, Long> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE courses c SET c.name_course = :nameCourse, c.professor_name = :professorName, " +
            " c.school_id = :schoolId WHERE c.id = :id", nativeQuery = true)
    int updateCourse(@Param("id") int id,
                     @Param("nameCourse") String nameCourse,
                     @Param("professorName") String professorName,
                     @Param("schoolId") int schoolId);

    @Query("SELECT c FROM Courses c WHERE c.schoolId = ?1")
    Courses findAllCoursesBySchoolId(int schoolId);

    Courses save(Courses course);

    Collection<Courses> findAll();
}
