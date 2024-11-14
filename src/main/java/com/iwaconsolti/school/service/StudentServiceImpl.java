package com.iwaconsolti.school.service;

import com.iwaconsolti.school.model.School;
import com.iwaconsolti.school.model.Student;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class StudentServiceImpl implements StudentService {

    @Override
    public List<Student> findStudents(School school) {
        return school.getStudents();
    }

    @Override
    public Student updateStudent(School school, int studentId, Student student) {
        for (Student s : school.getStudents()) {
            if (Objects.equals(s.getId(), studentId)) {
                s.setFirstName(student.getFirstName());
                s.setLastName(student.getLastName());
                s.setAge(student.getAge());
                return s;
            }
        }
        return null;
    }

}
