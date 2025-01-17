package com.iwaconsolti.school.demo.service;

import com.iwaconsolti.school.demo.model.Course;
import com.iwaconsolti.school.demo.model.School;
import com.iwaconsolti.school.demo.repository.CourseRepository;
import com.iwaconsolti.school.dto.CourseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
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

    public List<CourseDTO> getCourses(int schoolId) {
            List<Course> courses = courseRepository.findBySchoolId(schoolId);
        return courses.stream()
                .map(course -> new CourseDTO(
                        course.getId(),
                        course.getName(),
                        course.getProfessorName()))
                .collect(Collectors.toList());
    }

    public String createCourse(Course course){
        courseRepository.save(course);
        log.info("Course added successfully");
        return "Course added successfully: " + course.toString();
    }

    public String updateCourse(Course course) {
        courseRepository.save(course);
        log.info("Course update successfully");
        return "Course update successfully: " + course.toString() ;
    }

    public String deleteStudent(int idToDelete) {
        courseRepository.deleteById(idToDelete);
        log.info("Course delete with ID " +idToDelete+ " successfully!");
        return "Course delete with ID " +idToDelete+ " successfully!";
    }
}