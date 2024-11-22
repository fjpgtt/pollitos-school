package com.iwaconsolti.school.service;

import com.iwaconsolti.school.model.Course;
import com.iwaconsolti.school.model.Grade;
import com.iwaconsolti.school.model.School;
import com.iwaconsolti.school.model.Student;
import com.iwaconsolti.school.repository.CourseRepository;
import com.iwaconsolti.school.repository.GradeRepository;
import com.iwaconsolti.school.repository.SchoolRepository;
import com.iwaconsolti.school.repository.StudentRepository;
import org.hibernate.TransientPropertyValueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Optional<Course> getCourseById(int id, int schoolId) {
        return courseRepository.findByIdAndSchoolName(id, schoolId);
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
    public List<Grade> getAllGradesByStudent(int studentId, int schoolId) {
        return gradeRepository.findByStudentIdAndSchool(studentId, schoolId);
    }

    @Override
    public void deleteAllGradesByStudent(int studentId, int schoolId) {
        List<Grade> grades = gradeRepository.findByStudentIdAndSchool(studentId, schoolId);
        for (Grade grade : grades) {
            grade.setScore(0);
            gradeRepository.save(grade);
        }
    }

    @Override
    public void deleteAllGradesByCourse(int courseId, int schoolId) {
        List<Grade> grades = gradeRepository.findByCourseIdAndSchool(courseId, schoolId);
        for (Grade grade : grades) {
            grade.setScore(0);
            gradeRepository.save(grade);
        }
    }

    @Override
    public Grade createGrade(Grade grade) {
        try {
            return gradeRepository.save(grade);
        } catch (TransientPropertyValueException ex) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Cannot save grade: Ensure the course and student are already saved"
            );
        }
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
        studentRepository.deleteById(id);
    }
}
