package com.iwaconsolti.school.service;

import com.iwaconsolti.school.model.Course;
import com.iwaconsolti.school.model.Grade;
import com.iwaconsolti.school.model.School;
import com.iwaconsolti.school.model.Student;
import com.iwaconsolti.school.repository.CourseRepository;
import com.iwaconsolti.school.repository.GradeRepository;
import com.iwaconsolti.school.repository.SchoolRepository;
import com.iwaconsolti.school.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceImpl implements SchoolService, StudentService, CourseService, GradeService {

    private final SchoolRepository schoolRepository;
    private final StudentRepository studentRepository;
    private final GradeRepository gradeRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public ServiceImpl(SchoolRepository schoolRepository,
                       StudentRepository studentRepository,
                       GradeRepository gradeRepository,
                       CourseRepository courseRepository) {
        this.schoolRepository = schoolRepository;
        this.studentRepository = studentRepository;
        this.gradeRepository = gradeRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public School createSchool(School school) {
        return schoolRepository.save(school);
    }
//-------------------------------------------------------------------------------------------------------

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Optional<Course> getCourseById(int id) {
        return courseRepository.findById(id);
    }

    @Override
    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course updateCourse(int id, Course course) {
        if (courseRepository.existsById(id)) {
            course.setId(id);
            return courseRepository.save(course);
        }
        return null;
    }

    @Override
    public void deleteCourse(int id) {
        courseRepository.deleteById(id);
    }
//-------------------------------------------------------------------------------------------------------

    @Override
    public List<Grade> getGradesForStudent(int studentId) {
        return gradeRepository.findByStudentId(studentId);
    }

    @Override
    public void deleteGradesForStudent(int studentId) {
        List<Grade> grades = gradeRepository.findByStudentId(studentId);
        for (Grade grade : grades) {
            grade.setScore(0);
            gradeRepository.save(grade);
        }
    }

    @Override
    public void deleteGradesForCourse(int courseId) {
        List<Grade> grades = gradeRepository.findByCourseId(courseId);
        for (Grade grade : grades) {
            grade.setScore(0);
            gradeRepository.save(grade);
        }
    }

    @Override
    public Grade createGrade(Grade grade) {
        return gradeRepository.save(grade);
    }

    //-------------------------------------------------------------------------------------------------------
    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Optional<Student> getStudentById(int id) {
        return studentRepository.findById(id);
    }

    @Override
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student updateStudent(int id, Student student) {
        if (studentRepository.existsById(id)) {
            student.setId(id);
            return studentRepository.save(student);
        }
        return null;
    }

    @Override
    public void deleteStudent(int id) {
        studentRepository.deleteById(id);
    }
}
