package com.iwaconsolti.school.controller;

import com.iwaconsolti.school.model.Course;
import com.iwaconsolti.school.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping
    public ResponseEntity<?> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @PutMapping
    public ResponseEntity<?> editCourse(@RequestBody Course course){
        return ResponseEntity.ok(courseService.editCourse(course));
    }

}
