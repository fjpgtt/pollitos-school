package com.iwaconsolti.school.demo.service;

import com.iwaconsolti.school.demo.model.School;
import com.iwaconsolti.school.demo.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    private final List <Student> studentsList = new ArrayList<>();
    private final School school;

    @Autowired
    public StudentService(School school) {
        this.school = school;
    }

    public String saveStudent(int id, String firstName, String lastName, int age ){
        Student student = new Student(id, firstName, lastName, age);
        studentsList.add(student);
        return "Student added successfully: " + student.toString();
    }

    public String getStudents(){
        System.out.println(studentsList.toString());
        return studentsList.toString();
    }


}
