package com.iwaconsolti.school.miguel.controller;

import com.iwaconsolti.school.miguel.model.Courses;
import com.iwaconsolti.school.miguel.service.CourseService;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/app/{nameSchool}")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping("/course")
    public ResponseEntity<Courses> newCourse(@PathVariable String nameSchool, @RequestBody Courses course) {

        if (course.getNameCourse() == null || course.getNameCourse() == null || course.getProfessorName() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

         return ResponseEntity.status(HttpStatus.CREATED).body(courseService.createCourse(nameSchool,course));
    }

    @GetMapping("/course")
    public ResponseEntity<Collection<Courses>> listCourse(@PathVariable String nameSchool) {

        Collection<Courses> courses = courseService.getCourses(nameSchool);

        if(courses.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(courses);
    }

    @PutMapping("/course/{courseId}")
    public ResponseEntity<Courses> editCourse(@PathVariable String nameSchool,@PathVariable int courseId, @RequestBody Courses course){
        Courses updateCourse = courseService.editCourse(courseId, nameSchool, course);

        if(updateCourse == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(updateCourse);
    }
}