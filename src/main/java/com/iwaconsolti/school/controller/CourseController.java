package com.iwaconsolti.school.controller;

import com.iwaconsolti.school.model.Course;
import com.iwaconsolti.school.model.Student;
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
    public ResponseEntity<?> getCourses() {
        List<Course> courses = courseService.getCurses();
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCourseById(@PathVariable Integer id) {
        Course course = courseService.getCurseById(id);
        return ResponseEntity.ok(course);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getNameCourse(@PathVariable Integer id) {
        String courseName = courseService.getNameCourse(id);
        return ResponseEntity.ok(courseName);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProfessorName(@PathVariable Integer id) {
        String courseProfessor = courseService.getProfessorName(id);
        return ResponseEntity.ok(courseProfessor);
    }

    @PostMapping
    public ResponseEntity<?> addCourse(@RequestBody Course course) {
        courseService.addCurse(course);
        return ResponseEntity.status(201).body(courseService.getCurseById(course.getId()));
    }

    @PutMapping
    public ResponseEntity<?> setProfessorName(@RequestBody Course course){
        courseService.setProfessorName(course);
        return ResponseEntity.ok(200);
    }

    @PutMapping
    public ResponseEntity<?> setNameCourse(@RequestBody Course course){
        courseService.setNameCourse(course);
        return ResponseEntity.ok(200);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCourseById(@PathVariable Integer id){
        courseService.deleteCurseId(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAllCourses(){
        courseService.deleteAllCurses();
        return ResponseEntity.noContent().build();
    }
}
