package com.iwaconsolti.school.service;

import com.iwaconsolti.school.model.Course;
import com.iwaconsolti.school.model.Grade;
import com.iwaconsolti.school.model.School;
import com.iwaconsolti.school.model.Student;
import com.iwaconsolti.school.repository.CourseRepository;
import com.iwaconsolti.school.repository.GradeRepository;
import com.iwaconsolti.school.repository.SchoolRepository;
import com.iwaconsolti.school.repository.StudentRepository;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ServiceImpl implements SchoolService, StudentService, CourseService, GradeService {

    private final SchoolRepository schoolRepository;
    private final StudentRepository studentRepository;
    private final GradeRepository gradeRepository;
    private final CourseRepository courseRepository;

    @Value("${school.score.limit}")
    private int scoreLimit;

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

    @Override
    public Optional<School> findByName(String name) {
        return schoolRepository.findByName(name);
    }
    //-------------------------------------------------------------------------------------------------------

    @Override
    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public List<Course> findAllBySchoolId(int schoolId) {
        return courseRepository.findAllBySchoolId(schoolId);
    }

    @Override
    public Optional<Course> getCourseById(int id, int schoolId) {
        return courseRepository.findByIdAndSchoolId(id, schoolId);
    }

    @Override
    public Course updateCourse(int id, Course course, int schoolId) {
        log.info("The course information is being updated with the id: {}", course.getId());
        if (courseRepository.findByIdAndSchoolId(id, schoolId).isPresent()) {
            course.setId(id);
            return courseRepository.save(course);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found in this school");
    }

//-------------------------------------------------------------------------------------------------------

    @Override
    public List<Grade> getAllGradesByStudent(int studentId, int schoolId) {
        return gradeRepository.findByStudentIdAndSchool(studentId, schoolId);
    }

    @Override
    public void deleteAllGradesByStudent(int studentId, int schoolId) {
        log.info("Delete All Grades By Student: {}", studentId);
        List<Grade> grades = gradeRepository.findByStudentIdAndSchool(studentId, schoolId);
        for (Grade grade : grades) {
            grade.setScore(0);
            gradeRepository.save(grade);
        }
    }

    @Override
    public void deleteAllGradesByCourse(int courseId, int schoolId) {
        log.info("Delete All Grades By Course: {}", courseId);
        List<Grade> grades = gradeRepository.findByCourseIdAndSchool(courseId, schoolId);
        for (Grade grade : grades) {
            grade.setScore(0);
            gradeRepository.save(grade);
        }
    }

    @Override
    public Grade createGrade(Grade grade) {
        if (grade.getScore() > scoreLimit) {
            throw new IllegalArgumentException("Score exceeds the allowed limit of " + scoreLimit);
        }
        return gradeRepository.save(grade);
    }

    //-------------------------------------------------------------------------------------------------------
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
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found in this school");
    }

    @Override
    public void deleteStudent(int id) {
        log.info("The student with the ID is being deleted: {}", id);
        studentRepository.deleteById(id);
    }
}
