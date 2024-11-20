package com.iwaconsolti.school.miguel.service;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.iwaconsolti.school.miguel.persistence.model.Courses;
import com.iwaconsolti.school.miguel.persistence.model.Grade;
import com.iwaconsolti.school.miguel.persistence.model.School;
import com.iwaconsolti.school.miguel.persistence.model.Students;
import com.iwaconsolti.school.miguel.persistence.model.dto.GradesDTO;
import com.iwaconsolti.school.miguel.persistence.repository.GradeRepository;
import com.iwaconsolti.school.miguel.persistence.repository.SchoolRepository;
import lombok.extern.slf4j.Slf4j;
import org.h2.util.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.w3c.dom.ls.LSInput;

import java.util.*;

@Slf4j
@Service
public class GradeService {

    @Autowired
    private GradeRepository gradeRepository;

    private final School gerardoInstitute;
    private final School zetCollege;

    @Value("${school.score.limit:100}")
    private int limitGrade;

    public GradeService(@Qualifier("GerardoInstitute") School gerardoInstitute, @Qualifier("ZetCollege") School zetCollege){
        this.gerardoInstitute = gerardoInstitute;
        this.zetCollege = zetCollege;
    }

    public Grade createGrade(Grade grade) {
        return gradeRepository.save(grade);
    }

    public List<Map<String, Object>> findStudentById(int studentId) {
        return gradeRepository.findAllGradeByStudentId(studentId);
    }

    public Integer deleteGradeByStudentId(int studentId){
        return gradeRepository.deleteByStudentId(studentId);
    }

    public Integer deleteGradeByCourseId(int courseId){
        return gradeRepository.deleteByCourseId(courseId);
    }
}