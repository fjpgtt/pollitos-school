package com.iwaconsolti.school.demo.controller;

import com.iwaconsolti.school.demo.entity.StudentEntity;
import com.iwaconsolti.school.demo.model.Grade;
import com.iwaconsolti.school.demo.model.Student;
import com.iwaconsolti.school.demo.service.GradeService;
import com.iwaconsolti.school.demo.service.StudentInterface;
import com.iwaconsolti.school.demo.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@Slf4j
@RequiredArgsConstructor
@Profile("populated")
public class StudentsController {

    private final StudentService studentService;
    private final GradeService gradeService;

    //H2
    @Autowired
    StudentInterface studentInterface;

    //H2
    @GetMapping("/findAllStudents")
    public List<StudentEntity> findAllStudents(){
        return studentInterface.findAllStudents();
    }

    ////////////////////////////////////////////////////////////////////////////////////////
    @GetMapping()
    public ResponseEntity<List<Student>> getStudents(){
        log.info("Getting all the students");
        return ResponseEntity.ok(studentService.getStudents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable int id) {
        Student student = this.studentService.getById(id);
        log.info("Id: {}", id);
        log.info("Student: {}", student);
        if (student != null)
            return ResponseEntity.ok(student);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping()
    public ResponseEntity<Student> getStudents(@RequestBody Student student){
        log.info("Creating the student");
        student = this.studentService.createStudent(student);
        return ResponseEntity.ok(student);
    }

    @GetMapping("/{id}/grades")
    public ResponseEntity<Student> getGradesByUserId(@PathVariable int id){
        log.info("Getting the grades for student {}", id);
        Student student = this.studentService.getById(id);
        List<Grade> grades = this.gradeService.getGradesByStudentId(id);
        student.setGrades(grades);
        return ResponseEntity.ok(student);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> editStudent(@RequestBody Student student, @PathVariable int id){
        log.info("Editing the student");
        Student found = this.studentService.getById(id);
        if (found == null)
          return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        student.setId(found.getId());
        return ResponseEntity.ok(this.studentService.update(student));
    }

    @DeleteMapping("/{id}/grades")
    public ResponseEntity<Void> deleteAllGrades(@PathVariable int id){
      this.gradeService.deleteAllByStudentId(id);
      return ResponseEntity.noContent().build();
  }
}
