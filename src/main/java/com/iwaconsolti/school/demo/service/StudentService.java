package com.iwaconsolti.school.demo.service;

import com.iwaconsolti.school.demo.model.Student;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class StudentService {

    private final List<Student> students = new ArrayList<>();

    public StudentService() {
        Student eduardo = Student.builder().id(1).firstName("Eduardo").lastName("Gonzalez").age(24).creationDate(new Date()).build();
        Student gerardo = Student.builder().id(2).firstName("Gerardo").lastName("Gonzalez").age(20).creationDate(new Date()).build();
        this.students.add(eduardo);
        this.students.add(gerardo);
    }

    public List<Student> getStudents() {
        return this.students;
    }

    public Student getById(int id) {
        log.info("Id: {}", id);
        List<Student> students = this.students.stream().filter(student -> student.getId() == id).toList();
        log.info("Students: {}", students);
        if(!students.isEmpty())
            return students.getFirst();
        return null;
    }

    public Student createStudent(Student student){
        if(student != null){
            student.setCreationDate(new Date());
            this.students.add(student);
        }
        return student;
    }

}
