package com.iwaconsolti.school.demo.service;

import com.iwaconsolti.school.demo.model.Course;
import com.iwaconsolti.school.demo.model.Grade;
import com.iwaconsolti.school.demo.model.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class GradeService {

    private final List<Grade> grades = new ArrayList<>();

    private CourseService courseService;
    private StudentService studentService;

    public GradeService(StudentService studentService, CourseService courseService) {
        this.studentService = studentService;
        this.courseService = courseService;
        Grade primera = Grade.builder().id(1).score(100).studentId(1).courseId(1).creationDate(new Date()).build();
        Grade primera2 = Grade.builder().id(1).score(90).studentId(1).courseId(2).creationDate(new Date()).build();
        Grade segunda = Grade.builder().id(2).score(100).studentId(2).courseId(2).creationDate(new Date()).build();
        this.grades.add(primera);
        this.grades.add(primera2);
        this.grades.add(segunda);
    }

    public List<Grade> getGrades() {
        return this.grades;
    }

    public Grade getById(int id) {
        log.info("Id: {}", id);
        List<Grade> grades = this.grades.stream().filter(grade -> grade.getId() == id).toList();
        log.info("Grades: {}", grades);
        if(!grades.isEmpty())
            return grades.getFirst();
        return null;
    }

    public Grade createGrade(Grade grade){
        if(grade != null){
            grade.setCreationDate(new Date());
            this.grades.add(grade);
        }
        return grade;
    }

    public List<Grade> getGradesByStudentId(int studentId) {
        return this.grades.stream().filter(grade -> grade.getStudentId() == studentId).toList();
    }

    public List<Grade> getGradesByCourseId(int courseId) {
        return this.grades.stream().filter(grade -> grade.getCourseId() == courseId).toList();
    }

    public void deleteAllByCourseId(int courseId){
      this.grades.removeIf(grade -> grade.getCourseId() == courseId);
    }

    public void deleteAllByStudentId(int studentId){
      this.grades.removeIf(grade -> grade.getStudentId() == studentId);
  }

}
