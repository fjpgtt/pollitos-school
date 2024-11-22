package com.iwaconsolti.school.demo.service;

import com.iwaconsolti.school.demo.model.Grade;
import com.iwaconsolti.school.demo.model.School;
import com.iwaconsolti.school.demo.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.iwaconsolti.school.demo.repository.SchoolRepository;
import com.iwaconsolti.school.demo.repository.StudentRepository;
import java.util.List;

@Service
public class StudentService {
    private final School gerardoInstitute;
    private final School zetCollege;
    private final StudentRepository studentRepository;
    private final SchoolRepository schoolRepository;

    @Autowired
    public StudentService(@Qualifier("gerardoInstitute") School gerardoInstitute, @Qualifier("zetCollege") School zetCollege, Grade grade, StudentRepository studentRepository, SchoolRepository schoolRepository) {
        this.gerardoInstitute = gerardoInstitute;
        this.zetCollege = zetCollege;
        this.studentRepository = studentRepository;
        this.schoolRepository = schoolRepository;
    }

    public List<Student> getStudents(int schoolId) {
        return studentRepository.findBySchoolId(schoolId);
    }

    public String createStudent(Student student) {
        studentRepository.save(student);
        return "Student added successfully: " + student.toString();
    }

    public String updateStudent(Student student) {
        studentRepository.save(student);
        return "Student update successfully: " + student.toString() ;
    }

    public String deleteStudent(int idToDelete) {
        studentRepository.deleteById(idToDelete);
        return "Student delete with ID " +idToDelete+ " successfully!";
    }

    public String deleteAllGradesOfStudent(int idToDelete) {
        studentRepository.deleteAllByStudentId(idToDelete);
        return "All Grades delete of Student with ID " +idToDelete+ " successfully!";
    }

    public List<Grade> getAllGradesOfStudent(int studentId, int schoolId) {
        return studentRepository.findGradesByStudentAndSchool(studentId, schoolId);
    }
}
