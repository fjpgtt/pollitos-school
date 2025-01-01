package com.iwaconsolti.school.controller;

import com.iwaconsolti.school.controller.request.CourseRequest;
import com.iwaconsolti.school.controller.response.CourseResponse;
import com.iwaconsolti.school.model.Course;
import com.iwaconsolti.school.model.School;
import com.iwaconsolti.school.service.CourseService;
import com.iwaconsolti.school.service.SchoolHelperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/{schoolName}/course")
public class CourseController {

    private final CourseService courseService;
    private final SchoolHelperService schoolHelperService;

    @Autowired
    public CourseController(CourseService courseService, SchoolHelperService schoolHelperService) {
        this.courseService = courseService;
        this.schoolHelperService = schoolHelperService;
    }

    private Course convertRequestToCourse(CourseRequest courseRequest, School school) {
        return new Course(
                courseRequest.getName(),
                courseRequest.getProfessorName(),
                school
        );
    }

    private CourseResponse convertCourseToResponse(Course course) {
        return new CourseResponse(
                course.getId(),
                course.getName(),
                course.getProfessorName()
        );
    }

    @GetMapping
    public ResponseEntity<List<CourseResponse>> findAllCourses(@PathVariable String schoolName) {
        School school = schoolHelperService.findSchool(schoolName);
        List<CourseResponse> courseResponses = courseService
                .findAllBySchoolId(school.getId())
                .stream()
                .map(this::convertCourseToResponse)
                .toList();

        return new ResponseEntity<>(courseResponses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseResponse> findCourseById(@PathVariable String schoolName, @PathVariable int id) {
        School school = schoolHelperService.findSchool(schoolName);
        return courseService.getCourseById(id, school.getId())
                .map(this::convertCourseToResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CourseResponse> createCourse(@PathVariable String schoolName,
                                                       @RequestBody CourseRequest courseRequest) {
        School school = schoolHelperService.findSchool(schoolName);
        Course course = convertRequestToCourse(courseRequest, school);
        course = courseService.createCourse(course);
        CourseResponse courseResponse = convertCourseToResponse(course);

        return new ResponseEntity<>(courseResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseResponse> updateCourse(
            @PathVariable String schoolName,
            @PathVariable int id,
            @RequestBody CourseRequest courseRequest) {

        School school = schoolHelperService.findSchool(schoolName);
        Course updatedCourse = convertRequestToCourse(courseRequest, school);
        Course savedCourse = courseService.updateCourse(id, updatedCourse, school.getId());
        CourseResponse courseResponse = convertCourseToResponse(savedCourse);

        return new ResponseEntity<>(courseResponse, HttpStatus.OK);
    }

}
