package com.iwaconsolti.school.demo.controller;

import com.iwaconsolti.school.demo.model.School;
import com.iwaconsolti.school.demo.model.Student;
import com.iwaconsolti.school.demo.service.GradeService;
import com.iwaconsolti.school.demo.service.SchoolService;
import com.iwaconsolti.school.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/school")
public class StudentController {
    SchoolService schoolService;
    StudentService studentService;
    GradeService gradeService;

    @Autowired
    public StudentController(SchoolService schoolService, StudentService studentService, GradeService gradeService) {
        this.schoolService = schoolService;
        this.studentService = studentService;
        this.gradeService = gradeService;
    }

    @DeleteMapping("/{schoolName}/studentGradesDelete")
    public String deleteGradesStudent(
            @PathVariable String schoolName,
            @RequestParam int id
    ){
        School school = schoolService.getSchoolByName(schoolName);
        return studentService.deleteGradesStudent(school, id);
    }

    @PostMapping("/{schoolName}/studentsGrades") // Since this method only receives a @PathVariable and a @RequestParam, I do not consider @RequestBody necessary, since a JSON body is not being sent.
    public String studentGrades(
            @PathVariable String schoolName,
            @RequestParam int id){
        School school = schoolService.getSchoolByName(schoolName);
        return studentService.getStudentGrades(school, id);
    }

    @PutMapping("/{schoolName}/bodyStudent")
    public String updateStudent(
            @PathVariable String schoolName,
            @RequestBody Student student) {
        School school = schoolService.getSchoolByName(schoolName);
        return studentService.updateStudent(school, student);
    }

    @PostMapping("/{schoolName}/bodyStudent")
    public String createStudent(
            @PathVariable String schoolName,
            @RequestBody Student student) {
        School school = schoolService.getSchoolByName(schoolName);
        return studentService.createStudent(school, student);
    }


    @PutMapping("/{schoolName}/students") //Some methods were updated to @RequestBody and the previous ones were preserved to exemplify another way of doing it by @RequestParam
    public String updateStudent(
            @PathVariable String schoolName,
            @RequestParam int id,
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam int age){

        School school = schoolService.getSchoolByName(schoolName);
        Student student = new Student(id, firstName, lastName, age);
        return studentService.updateStudent(school, student);
    }

    @PostMapping("/{schoolName}/students")
    public String createStudent(
            @PathVariable String schoolName,
            @RequestParam int id,
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam int age) {

        School school = schoolService.getSchoolByName(schoolName);
        Student student = new Student(id, firstName, lastName, age);
        return studentService.createStudent(school, student);
    }

    @GetMapping("/{schoolName}/getStudents")
    public String returnStudents(@PathVariable String schoolName){ // Obtener la escuela según el nombre
        return studentService.getStudents(schoolName);
    }
}
