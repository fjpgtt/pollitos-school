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

    private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    List<Student> students = new ArrayList<>(Arrays.asList(
            new Student(1, "Adrian", "Sanchez", 24),
            new Student(2, "Francisco", "Perez", 18),
            new Student(3, "aaaaaa", "lastaaaa", 18),
            new Student(4, "bbbbbb", "lastbbbbb", 18)
    ));

    @PostConstruct
    public void init() {
        logger.info("StudentServiceImpl has initialized");
    }

    @Override
    public List<Student> getStudents() {
        return students;
    }

    @Override
    public Student getStudentById(Integer id) {
        for (Student s : students) {
            if (Objects.equals(s.getId(), id)) {
                return s;
            }
        }
        logger.error("Student not found with ID: {}", id);
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found with ID: " + id);

    }

    @Override
    public Student setStudent(Student student) {
        for (Student s : students) {
            if (Objects.equals(s.getId(), student.getId())) {
                s.setFirstName(student.getFirstName());
                s.setLastName(student.getLastName());
                s.setAge(student.getAge());
                break;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found with ID: " + student.getId());
    }

    @Override
    public Student addStudent(Student student) {
        students.add(student);
        return student;
    }

    @Override
    public void deleteStudent(Integer id) {
        students.removeIf(student -> Objects.equals(student.getId(), id));
    }
}
