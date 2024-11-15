package com.iwaconsolti.school.miguel.service;

import com.iwaconsolti.school.miguel.model.School;
import com.iwaconsolti.school.miguel.model.Students;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@Slf4j
@Service
public class StudentService {

    private final School gerardoInstitute;
    private final School zetCollege;

    public StudentService(@Qualifier("GerardoInstitute") School gerardoInstitute, @Qualifier("ZetCollege") School zetCollege){
        this.gerardoInstitute = gerardoInstitute;
        this.zetCollege = zetCollege;
    }

    public Students createStudents(String schoolName, Students student) {
        if("GerardoInstitute".equalsIgnoreCase(schoolName)){
            if(gerardoInstitute.getStudents().containsKey(student.getId())){
                return gerardoInstitute.getStudents().get(student.getId());
            }
            gerardoInstitute.getStudents().put(student.getId(), student);
            log.info("The student registered successfully in Gerardo Institute {}",student);
        }else if("ZetCollege".equalsIgnoreCase(schoolName)){
            zetCollege.getStudents().put(student.getId(), student);
            log.info("The student registered successfully in Zet College {}",student);
        }
        return student;
    }

    public Students getStudentById(String schoolName, int studentId){
        if("GerardoInstitute".equalsIgnoreCase(schoolName)){
            return gerardoInstitute.getStudents().get(studentId);
        }else if("ZetCollege".equalsIgnoreCase(schoolName)) {
            return zetCollege.getStudents().get(studentId);
        }
        return null;
    }

    public Collection<Students> getStudents(String schoolName) {
        if("GerardoInstitute".equalsIgnoreCase(schoolName)){
            return new ArrayList<>(gerardoInstitute.getStudents().values());
        }else if("ZetCollege".equalsIgnoreCase(schoolName)) {
            return new ArrayList<>(zetCollege.getStudents().values());
        }
        return Collections.emptyList();
    }

    public Students editStudent(int id, String schoolName, Students student){
        if("GerardoInstitute".equalsIgnoreCase(schoolName)){
            gerardoInstitute.getStudents().put(id,student);
            log.info("The student was edited successfully in Gerardo Institute {}",student);
        }else if("ZetCollege".equalsIgnoreCase(schoolName)) {
            zetCollege.getStudents().put(id,student);
            log.info("The student was edited successfully in Zet College {}",student);

        }
        return student;
    }
}