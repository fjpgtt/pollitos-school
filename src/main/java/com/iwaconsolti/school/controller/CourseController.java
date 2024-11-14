package com.iwaconsolti.school.controller;

import com.iwaconsolti.school.controller.response.CourseResponse;
import com.iwaconsolti.school.model.Course;
import com.iwaconsolti.school.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/{schoolName}/course")
public class CourseController {

    @Autowired
    @Qualifier("gerardoService")
    private SchoolService gerardoService;

    @Autowired
    @Qualifier("zetService")
    private SchoolService zetService;

    private SchoolService getServiceBySchoolName(String schoolName) {
        if ("GerardoInstitute".equalsIgnoreCase(schoolName)) {
            return gerardoService;
        } else if ("ZetCollege".equalsIgnoreCase(schoolName)) {
            return zetService;
        } else {
            throw new IllegalArgumentException("Invalid school name");
        }
    }

    private CourseResponse convertToCourseResponse(Course course) {
        return new CourseResponse(course.getId(), course.getName(), course.getProfessorName());
    }

    private Course convertToCourse(CourseResponse courseResponse) {
        return new Course(courseResponse.getId(), courseResponse.getName(), courseResponse.getProfessorName());
    }

    @GetMapping
    public List<CourseResponse> getAllCourses(@PathVariable String schoolName) {
        List<Course> courses = getServiceBySchoolName(schoolName).findCourses();
        return courses.stream()
                .map(course -> new CourseResponse(course.getId(), course.getName(), course.getProfessorName()))
                .collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<CourseResponse> createCourse(@PathVariable String schoolName, @RequestBody CourseResponse courseResponse) {
        Course course = convertToCourse(courseResponse);
        if (getServiceBySchoolName(schoolName).createCourse(course) != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(courseResponse);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PutMapping("/{id}")
    public void editCourse(@PathVariable String schoolName, @PathVariable int id, @RequestBody CourseResponse updatedCourseResponse) {
        Course updatedCourse = convertToCourse(updatedCourseResponse);
        getServiceBySchoolName(schoolName).updateCourse(id, updatedCourse);
    }
}
