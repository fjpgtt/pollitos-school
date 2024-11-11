package com.iwaconsolti.school.service;

import com.iwaconsolti.school.model.Student;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Profile("populated")
@Service
public class StudentServiceImpl implements StudentService {

    List<Student> students;

    @Override
    public List<Student> getStudents() {
        return students;
    }

    @Override
    public Student editStudent(Student student) {
        for (Student s : students) {
            if (Objects.equals(s.getId(), student.getId())) {
                if (student.getFirstName() != null) {
                    s.setFirstName(student.getFirstName());
                }
                if (student.getLastName() != null) {
                    s.setLastName(student.getLastName());
                }
                if (student.getAge() != null) {
                    s.setAge(student.getAge());
                }
                return student;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found with ID: " + student.getId());
    }

}
