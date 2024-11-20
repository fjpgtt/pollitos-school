package com.iwaconsolti.school.miguel.controller;

import com.iwaconsolti.school.miguel.persistence.model.Courses;
import com.iwaconsolti.school.miguel.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/app/{nameSchool}")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping("/course")
    public ResponseEntity<Object> newCourse(@PathVariable String nameSchool, @RequestBody Courses course) {

        if (course.getNameCourse() == null || course.getProfessorName() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    Map.of(
                            "message","Required data is missing", "stautus", HttpStatus.BAD_REQUEST
                    )
            );
        }
        Courses objCourse = courseService.createCourse(nameSchool,course);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                Map.of(
                        "message", "The Course registered successfully","status", HttpStatus.CREATED,
                        "course", objCourse
                )
        );
    }

    @GetMapping("/course")
    public ResponseEntity<Object> listCourse(@PathVariable String nameSchool) {

        Courses courses = courseService.getCourses(nameSchool);

        if(courses == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    Map.of(
                            "message","there are no registered courses","status",HttpStatus.NOT_FOUND
                    )
            );
        }
        return ResponseEntity.ok(courses);
    }

    @PutMapping("/course/{courseId}")
    public ResponseEntity<Object> editCourse(@PathVariable String nameSchool,@PathVariable int courseId, @RequestBody Courses course){

        if(course.getNameCourse() == null || course.getProfessorName() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    Map.of("message", "Required data is missing", "status", HttpStatus.BAD_REQUEST)
            );
        }

        Integer updateCourse = courseService.editCourse(courseId, nameSchool, course);

        if(updateCourse == 0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    Map.of(
                            "message","The course to modify was not found","status", HttpStatus.NOT_FOUND
                    )
            );
        }

        return ResponseEntity.ok(
                Map.of(
                        "message", "The course was updated successfully", "status", HttpStatus.ACCEPTED
                )
        );
    }
}