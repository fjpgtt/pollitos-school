package com.iwaconsolti.school.miguel.controller;

import com.iwaconsolti.school.miguel.model.Courses;
import com.iwaconsolti.school.miguel.model.dto.CoursesDTO;
import com.iwaconsolti.school.miguel.service.CourseService;
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
    public ResponseEntity<Courses> newCourse(@PathVariable String nameSchool, @RequestBody CoursesDTO courseDTO) {

        if (courseDTO.getNameCourse() == null || courseDTO.getProfessorName() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

         return ResponseEntity.status(HttpStatus.CREATED).body(courseService.createCourse(nameSchool,courseDTO));
    }

    @GetMapping("/course")
    public ResponseEntity<Collection<CoursesDTO>> listCourse(@PathVariable String nameSchool) {

        Collection<CoursesDTO> courses = courseService.getCourses(nameSchool);

        if(courses.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(courses);
    }

    @PutMapping("/course/{courseId}")
    public ResponseEntity<Courses> editCourse(@PathVariable String nameSchool,@PathVariable int courseId, @RequestBody CoursesDTO courseDTO){

        Courses updateCourse = courseService.editCourse(courseId, nameSchool, courseDTO);

        if(updateCourse == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(updateCourse);
    }
}