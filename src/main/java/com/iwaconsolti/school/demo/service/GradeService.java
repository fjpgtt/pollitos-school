package com.iwaconsolti.school.demo.service;

import com.iwaconsolti.school.demo.entity.GradeEntity;
import com.iwaconsolti.school.demo.entity.repository.GradeRepository;
import com.iwaconsolti.school.demo.model.Grade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class GradeService implements GradeInterface {

    @Autowired
    GradeRepository gradeRepository;

    private final List<Grade> grades = new ArrayList<>();

    private CourseService courseService;
    private StudentService studentService;

    public GradeService(StudentService studentService, CourseService courseService) {
        this.studentService = studentService;
        this.courseService = courseService;
        Grade primera = Grade.builder().id(1).score(100).studentId(4).courseId(1).creationDate(new Date()).build();
        Grade segunda = Grade.builder().id(2).score(100).studentId(3).courseId(2).creationDate(new Date()).build();
        Grade tercera = Grade.builder().id(3).score(90).studentId(2).courseId(3).creationDate(new Date()).build();
        Grade cuarta = Grade.builder().id(4).score(70).studentId(1).courseId(4).creationDate(new Date()).build();
        Grade quinta = Grade.builder().id(5).score(50).studentId(2).courseId(3).creationDate(new Date()).build();
        Grade sexta = Grade.builder().id(6).score(77).studentId(3).courseId(2).creationDate(new Date()).build();


        this.grades.add(primera);
        this.grades.add(segunda);
        this.grades.add(tercera);
        this.grades.add(cuarta);
        this.grades.add(quinta);
        this.grades.add(sexta);
    }

    //H2
    @Override
    public List<GradeEntity> findAllGrades(){
        return gradeRepository.findAll();
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
