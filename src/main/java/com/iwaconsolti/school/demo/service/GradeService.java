package com.iwaconsolti.school.demo.service;

import com.iwaconsolti.school.demo.model.Grade;
import com.iwaconsolti.school.demo.model.School;
import com.iwaconsolti.school.demo.repository.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeService {
    private final School gerardoInstitute;
    private final School zetCollege;
    private final GradeRepository gradeRepository;

    @Autowired
    public GradeService(@Qualifier("gerardoInstitute") School gerardoInstitute, @Qualifier("zetCollege") School zetCollege, GradeRepository gradeRepository) {
        this.gerardoInstitute = gerardoInstitute;
        this.zetCollege = zetCollege;
        this.gradeRepository = gradeRepository;
    }

    public List<Grade> getGrades(int schoolId) {
        return gradeRepository.findBySchoolId(schoolId);
    }

    public String createGrade(Grade grade) {
        gradeRepository.save(grade);
        return "Grade added successfully: " + grade.toString();
    }

    public String updateGrade(Grade grade) {
        gradeRepository.save(grade);
        return "Grade update successfully: " + grade.toString() ;
    }

    public String deleteGrade(int idToDelete) {
        gradeRepository.deleteById(idToDelete);
        return "Grade delete with ID " +idToDelete+ " successfully!";
    }

    public String deleteAllGradesOfCourse(int idToDelete) {
        gradeRepository.deleteAllByCourseId(idToDelete);
        return "Grade delete with ID " +idToDelete+ " successfully!";
    }
}
