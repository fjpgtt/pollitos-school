package com.iwaconsolti.school.miguel.controller;

import com.iwaconsolti.school.miguel.model.Courses;
import com.iwaconsolti.school.miguel.model.dto.CoursesDTO;
import com.iwaconsolti.school.miguel.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;

@RestController
@RequestMapping("/app/{nameSchool}/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping("/")
    public ResponseEntity<Object> newCourse(@PathVariable String nameSchool, @RequestBody CoursesDTO courseDTO) {

        if (courseDTO.getNameCourse() == null || courseDTO.getProfessorName() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    Map.of(
                            "message","a required piece of information is missing"
                    )
            );
        }else if(courseService.createCourse(nameSchool, courseDTO) != null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    Map.of(
                            "message", "The provided id is already registered"
                    )
            );
        }
        Courses course = courseService.createCourse(nameSchool,courseDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                Map.of(
                        "message", "The course registered successfully",
                        "status", HttpStatus.CREATED.value()
                )
        );
    }

    @GetMapping("/")
    public ResponseEntity<Collection<CoursesDTO>> listCourse(@PathVariable String nameSchool) {

        Collection<CoursesDTO> courses = courseService.getCourses(nameSchool);

        if(courses.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(courses);
    }

    @PutMapping("/{courseId}")
    public ResponseEntity<Object> editCourse(@PathVariable String nameSchool,@PathVariable int courseId, @RequestBody CoursesDTO courseDTO){

        Courses updateCourse = courseService.editCourse(courseId, nameSchool, courseDTO);

        if(updateCourse == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    Map.of(
                            "message","Course ID does not exist"
                    )
            );
        }

        return ResponseEntity.ok(updateCourse);
    }
}