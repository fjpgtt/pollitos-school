package com.iwaconsolti.school.miguel.controller;

import com.iwaconsolti.school.miguel.model.Courses;
import com.iwaconsolti.school.miguel.model.Students;
import com.iwaconsolti.school.miguel.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app/school")
public class SchoolController {

    @Autowired
    private SchoolService schoolService;


    @PostMapping("/newstudent")
    public Students newStudent(@RequestBody Students student) {
        return schoolService.createrStudents(student);
    }

    // Obtener todos los estudiantes
    @GetMapping("/liststudents")
    public List<Students> listStudents() {
        return schoolService.getStudents();
    }

    @PostMapping("/registercourse")
    public Courses newCourse(@RequestBody Courses course){
        return schoolService.createCourse(course);
    }

    @GetMapping("/listcourse")
    public List<Courses> listCourse() {
        return schoolService.getCourses();
    }
}
