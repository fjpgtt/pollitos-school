package com.iwaconsolti.school.demo.repository;

import com.iwaconsolti.school.demo.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

    List<Course> findBySchoolId(int schoolId);

}
