package com.iwaconsolti.school.service;

import com.iwaconsolti.school.model.School;
import com.iwaconsolti.school.model.Student;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@Service
public class StudentServiceImpl implements StudentService {

    List<Student> students;

    @Override
    public List<Student> getStudents(School school) {
        return school.getStudents();
    }

    @Override
    public Student editStudent(School school, int studentId, Student student) {
        for (Student s : school.getStudents()) {
            if (Objects.equals(s.getId(), studentId)) {
                if (student.getFirstName() != null) {
                    s.setFirstName(student.getFirstName());
                }
                if (student.getLastName() != null) {
                    s.setLastName(student.getLastName());
                }
                if (student.getAge() != null) {
                    s.setAge(student.getAge());
                }
                return s;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found with ID: " + studentId);
    }

}
