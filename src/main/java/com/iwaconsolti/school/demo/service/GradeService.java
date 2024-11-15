package com.iwaconsolti.school.demo.service;

import com.iwaconsolti.school.demo.model.Grade;
import com.iwaconsolti.school.demo.model.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class GradeService {
    private final School gerardoInstitute;
    private final School zetCollege;

    @Autowired
    public GradeService(@Qualifier("gerardoInstitute") School gerardoInstitute, @Qualifier("zetCollege") School zetCollege) {
        this.gerardoInstitute = gerardoInstitute;
        this.zetCollege = zetCollege;
    }

    public String createGrade(School school, Grade grade) {
        for (Grade existingGrade : school.getGradeList()) {
            if (existingGrade.getMaxScore() >= 0 && existingGrade.getStudent() == grade.getStudent() && existingGrade.getCourse() == grade.getCourse()) {
                return "Grade not added; already exists: " + grade.toString();
            }
        }
        school.getGradeList().add(grade);
        return "Grade added successfully: " + grade.toString();
    }

    public String getGrades(String schoolName){
        if ("GerardoInstitute".equalsIgnoreCase(schoolName)) {
            return gerardoInstitute.getGradeList().toString();
        } else if ("ZetCollege".equalsIgnoreCase(schoolName)) {
            return zetCollege.getGradeList().toString();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "School not found");
        }
    }
}
