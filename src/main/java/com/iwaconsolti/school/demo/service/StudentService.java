package com.iwaconsolti.school.demo.service;

import com.iwaconsolti.school.demo.model.Student;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    private Student student;

    public String saveStudent(int id, String firstName, String lastName, int age ){
        //Student student =new Student(id, firstName, lastName, age);
        return "HI" + id +"|"+ firstName +"|"+ lastName +"|"+ age;
    }


}
