package com.iwaconsolti.school.demo.service;

import com.iwaconsolti.school.demo.model.Grade;
import com.iwaconsolti.school.demo.model.Student;
import com.iwaconsolti.school.dto.GradeDTO;
import com.iwaconsolti.school.dto.StudentDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.iwaconsolti.school.demo.repository.StudentRepository;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<StudentDTO> getStudents(int schoolId) {
        List<Student>  students = studentRepository.findBySchoolId(schoolId);
        return students.stream()
                .map(student -> new StudentDTO(
                        student.getId(),
                        student.getFirstName(),
                        student.getLastName(),
                        student.getAge()))
                .collect(Collectors.toList());
    }

    public String createStudent(Student student) {
        studentRepository.save(student);
        log.info("Student added successfully");
        return "Student added successfully: " + student.toString();
    }

    public String updateStudent(Student student) {
        studentRepository.save(student);
        log.info("Student update successfully:");
        return "Student update successfully: " + student.toString() ;
    }

    public String deleteStudent(int idToDelete) {
        studentRepository.deleteById(idToDelete);
        log.info("Student delete with ID"+idToDelete+ " successfully!");
        return "Student delete with ID " +idToDelete+ " successfully!";
    }

    public String deleteAllGradesOfStudent(int idToDelete) {
        studentRepository.deleteAllByStudentId(idToDelete);
        log.info("All Grades delete of Student with ID " +idToDelete+ " successfully!");
        return "All Grades delete of Student with ID " +idToDelete+ " successfully!";
    }

    public List<GradeDTO> getAllGradesOfStudent(int studentId, int schoolId) {
        List<Grade> grades = studentRepository.findGradesByStudentAndSchool(studentId, schoolId);
        return grades.stream()
                .map(grade -> new GradeDTO(
                        grade.getId(),
                        grade.getScore(),
                        grade.getStudentId(),
                        grade.getCourseId()))
                .collect(Collectors.toList());
    }
}
