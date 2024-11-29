package com.iwaconsolti.school.demo.service;

import com.iwaconsolti.school.demo.model.Grade;
import com.iwaconsolti.school.demo.model.School;
import com.iwaconsolti.school.demo.repository.GradeRepository;
import com.iwaconsolti.school.dto.GradeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<GradeDTO> getGrades(int schoolId) {
        List<Grade> grades = gradeRepository.findBySchoolId(schoolId);
        return grades.stream()
                .map(grade -> new GradeDTO(
                        grade.getId(),
                        grade.getScore(),
                        grade.getStudentId(),
                        grade.getCourseId()))
                .collect(Collectors.toList());
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
