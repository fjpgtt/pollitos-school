package com.iwaconsolti.school.miguel.controller;

import com.iwaconsolti.school.miguel.model.Courses;
import com.iwaconsolti.school.miguel.model.Grade;
import com.iwaconsolti.school.miguel.model.Students;
import com.iwaconsolti.school.miguel.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/app/school")
public class SchoolController {

    @Autowired
    private SchoolService schoolService;


    @PostMapping("/{nameSchool}/register/student")
    public Students registerStudent(@PathVariable String nameSchool,@RequestBody Students student) {
        return schoolService.createrStudents(nameSchool,student);
    }


    @GetMapping("/{nameSchool}/list/students")
    public Collection<Students> listStudents(@PathVariable String nameSchool) {
        return schoolService.getStudents(nameSchool);
    }

    @PostMapping("/{nameSchool}/register/course")
    public Courses newCourse(@PathVariable String nameSchool,@RequestBody Courses course){
        return schoolService.createCourse(nameSchool,course);
    }

    @GetMapping("/{nameSchool}/list/course")
    public Collection<Courses> listCourse(@PathVariable String nameSchool) {
        return schoolService.getCourses(nameSchool);
    }

    @PostMapping("/{nameSchool}/grades")
    public Grade createGrade(@PathVariable String nameSchool,@RequestBody Grade grade){
        return schoolService.createGrade(nameSchool, grade);
    }

    @GetMapping("/{nameSchool}/list/grade/student/{studentId}")
    public List<String> getGradesByStudentId(@PathVariable String nameSchool,@PathVariable Integer studentId){
        return schoolService.getGradesByStudent(nameSchool, studentId);
    }

    @PutMapping("/{nameSchool}/edit/course/{courseId}")
    public Courses editCourse(@PathVariable String nameSchool,@PathVariable int courseId, @RequestBody Courses course){
        return schoolService.editCourse(courseId, nameSchool, course);
    }

    @PutMapping("/{nameSchool}/edit/students/{studentId}")
    public Students editStudent(@PathVariable String nameSchool,@PathVariable int studentId,@RequestBody Students student){
        return schoolService.editStudent(studentId, nameSchool, student);
    }

    @DeleteMapping("/{nameSchool}/delete/grades/student/{studentId}")
    public boolean deleteGradesByStudentId(@PathVariable String nameSchool,@PathVariable int studentId){
        return schoolService.deleteGradeByStudentId(nameSchool, studentId);
    }

    @DeleteMapping("/{nameSchool}/delete/grades/course/{courseId}")
    public boolean deleteGradesByCourseId(@PathVariable String nameSchool,@PathVariable int courseId){
        return schoolService.deleteGradeByCourseId(nameSchool, courseId);
    }
}
