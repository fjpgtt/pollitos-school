package com.iwaconsolti.school.controller;

import com.iwaconsolti.school.controller.response.CourseRequest;
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
@RequestMapping("/{schoolName}/courses")
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
            throw new IllegalArgumentException("Invalid school name: " + schoolName);
        }
    }

    private CourseRequest convertToCourseResponse(Course course) {
        return new CourseRequest(course.getId(), course.getName(), course.getProfessorName());
    }

    private Course convertToCourseRequest(CourseRequest courseRequest) {
        return new Course(courseRequest.getId(), courseRequest.getName(), courseRequest.getProfessorName());
    }

    @GetMapping
    public List<CourseRequest> findAllCourses(@PathVariable String schoolName) {
        List<Course> courses = getServiceBySchoolName(schoolName).findCourses();
        return courses.stream()
                .map(this::convertToCourseResponse)
                .collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<CourseRequest> createCourse(@PathVariable String schoolName, @RequestBody CourseRequest courseResponse) {
        Course courseRequest = convertToCourseRequest(courseResponse);
        Course createdCourse = getServiceBySchoolName(schoolName).createCourse(courseRequest);

        if (createdCourse != null) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(convertToCourseResponse(createdCourse));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> editCourse(@PathVariable String schoolName, @PathVariable int id, @RequestBody CourseRequest updatedCourseRequest) {
        Course updatedCourse = convertToCourseRequest(updatedCourseRequest);
        boolean updated = getServiceBySchoolName(schoolName).updateCourse(id, updatedCourse);

        if (updated) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
