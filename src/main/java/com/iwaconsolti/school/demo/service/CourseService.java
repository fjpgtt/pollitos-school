package com.iwaconsolti.school.demo.service;

import com.iwaconsolti.school.demo.model.Course;
import com.iwaconsolti.school.demo.model.School;
import com.iwaconsolti.school.demo.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService{
        private final School gerardoInstitute;
        private final School zetCollege;
    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(@Qualifier("gerardoInstitute") School gerardoInstitute, @Qualifier("zetCollege") School zetCollege, CourseRepository courseRepository) {
        this.gerardoInstitute = gerardoInstitute;
        this.zetCollege = zetCollege;
        this.courseRepository = courseRepository;
    }

    public List<Course> getCourses(int schoolId) {
            return courseRepository.findBySchoolId(schoolId);
    }

    public String createCourse(Course course){
        courseRepository.save(course);
        return "Course added successfully: " + course.toString();
    }

    public String updateCourse(Course course) {
        courseRepository.save(course);
        return "Course update successfully: " + course.toString() ;
    }

    public String deleteStudent(int idToDelete) {
        courseRepository.deleteById(idToDelete);
        return "Course delete with ID " +idToDelete+ " successfully!";
    }
}