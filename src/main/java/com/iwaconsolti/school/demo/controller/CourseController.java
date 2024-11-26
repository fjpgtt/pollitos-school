package com.iwaconsolti.school.demo.controller;

import com.iwaconsolti.school.demo.model.Course;
import com.iwaconsolti.school.demo.repository.CourseRepository;
import com.iwaconsolti.school.demo.service.CourseService;
import com.iwaconsolti.school.demo.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/school")
public class CourseController {
    private final  CourseService courseService;
    private final SchoolService schoolService;
    private final CourseRepository courseRepository;

    @Autowired
    public CourseController(CourseService courseService, SchoolService schoolService, CourseRepository courseRepository) {
        this.courseService = courseService;
        this.schoolService = schoolService;
        this.courseRepository = courseRepository;
    }

    @GetMapping("/{schoolName}/allCourses")
    public ResponseEntity<List<CourseRepository.CourseDTO>> returnCourses(@PathVariable String schoolName) {
        return ResponseEntity.ok(courseService.getCourses(schoolService.getSchoolByName(schoolName)));
    }

    @PostMapping("/newCourse")
    public ResponseEntity<String> newCourse(@RequestBody Course course){
        return ResponseEntity.ok(courseService.createCourse(course));
    }

    @PutMapping("/editCourse")
    public ResponseEntity<String> editCourse(@RequestBody Course course){
        System.out.println("Student: "+ course.toString());
        return ResponseEntity.ok(courseService.updateCourse(course));
    }

    @DeleteMapping("/{id}/eraseCourse")
    public ResponseEntity<String> eraseCourse(@PathVariable int id){
        System.out.println("idToDelete"+ id);
        return ResponseEntity.ok(courseService.deleteStudent(id));
    }
}


