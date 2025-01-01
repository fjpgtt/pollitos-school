package com.iwaconsolti.school.service;

import com.iwaconsolti.school.model.Grade;
import com.iwaconsolti.school.repository.GradeRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Slf4j
@Service
public class GradeServiceImpl implements GradeService {
    private final GradeRepository gradeRepository;

    @Value("${school.score.limit}")
    private int scoreLimit;

    @Autowired
    public GradeServiceImpl(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

    @Override
    public List<Grade> getAllGradesByStudent(int studentId, int schoolId) {
        return gradeRepository.findByStudentIdAndSchool(studentId, schoolId);
    }

    @Override
    public Grade createGrade(Grade grade) {
        if (grade.getScore() > scoreLimit) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return gradeRepository.save(grade);
    }

    @Transactional
    @Override
    public void deleteAllGradesByStudent(int studentId, int schoolId) {
        List<Grade> grades = gradeRepository.findByStudentIdAndSchool(studentId, schoolId);
        if (!grades.isEmpty()) {
            gradeRepository.deleteByStudentIdAndSchoolId(studentId, schoolId);
            log.info("Successfully deleted all grades for Student ID: {} in School ID: {}", studentId, schoolId);
        } else {
            log.warn("No grades found for Student ID: {} in School ID: {}", studentId, schoolId);
        }
    }

    @Transactional
    @Override
    public void deleteAllGradesByCourse(int courseId, int schoolId) {
        List<Grade> grades = gradeRepository.findByCourseIdAndSchool(courseId, schoolId);
        if (!grades.isEmpty()) {
            gradeRepository.deleteByCourseIdAndSchoolId(courseId, schoolId);
            log.info("Successfully deleted all grades for Course ID: {} in School ID: {}", courseId, schoolId);
        } else {
            log.warn("No grades found for Course ID: {} in School ID: {}", courseId, schoolId);
        }
    }
}
