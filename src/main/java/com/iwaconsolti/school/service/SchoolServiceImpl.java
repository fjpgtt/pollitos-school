package com.iwaconsolti.school.service;

import com.iwaconsolti.school.model.Course;
import com.iwaconsolti.school.model.Grade;
import com.iwaconsolti.school.model.School;
import com.iwaconsolti.school.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolServiceImpl implements SchoolService {

    private final School school;

    private final StudentService studentService;
    private final CourseService courseService;
    private final GradeService gradeService;

    public SchoolServiceImpl(School school, StudentService studentService, CourseService courseService, GradeService gradeService) {
        this.school = school;
        this.studentService = studentService;
        this.courseService = courseService;
        this.gradeService = gradeService;
    }

    @Override
    public void addStudent(Student student) {
       // studentService.addStudent(student); // Utiliza el servicio StudentService
    }

    @Override
    public List<Student> getAllStudents() {
        return studentService.getStudents(); // Llama al servicio para obtener estudiantes
    }

    @Override
    public void editStudent(Integer id, Student updatedStudent) {
        studentService.editStudent(updatedStudent); // Edita el estudiante
    }

    @Override
    public void addCourse(Course course) {
        //courseService.addCourse(course); // Llama al servicio CourseService
    }

    @Override
    public List<Course> getAllCourses() {
        return courseService.getAllCourses(); // Llama al servicio CourseService
    }

    @Override
    public void editCourse(Integer id, Course updatedCourse) {
        courseService.editCourse(updatedCourse); // Edita el curso
    }

    @Override
    public void addGrade(Grade grade) {
        //gradeService.addGrade(grade); // Llama al servicio GradeService
    }

    @Override
    public List<Grade> getGradesByStudent(Integer studentId) {
        return gradeService.getGradesByStudent(studentId); // Delegar a GradeService
    }

    @Override
    public void deleteGradesByStudent(Integer studentId) {
        gradeService.deleteAllGradesOfStudent(studentId); // Delegar a GradeService
    }

    @Override
    public void deleteGradesByCourse(Integer courseId) {
        gradeService.deleteAllGradesOfCourse(courseId); // Delegar a GradeService
    }
}
