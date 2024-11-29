package com.iwaconsolti.school.service;

import com.iwaconsolti.school.model.Student;
import com.iwaconsolti.school.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> getAllStudentsBySchool(int schoolId) {
        return studentRepository.findAllBySchoolId(schoolId);
    }

    @Override
    public Optional<Student> getStudentById(int id, int schoolId) {
        return studentRepository.findByIdAndSchoolId(id, schoolId);
    }

    @Override
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student updateStudent(int id, Student student, int schoolId) {
        if (studentRepository.findByIdAndSchoolId(id, schoolId).isPresent()) {
            student.setId(id);
            return studentRepository.save(student);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @Override
    public void deleteStudent(int id) {
        log.info("The student with the ID is being deleted: {}", id);
        studentRepository.deleteById(id);
    }
}
