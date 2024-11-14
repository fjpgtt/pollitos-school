package com.iwaconsolti.school.miguel.service;

import com.iwaconsolti.school.miguel.model.Grade;
import com.iwaconsolti.school.miguel.model.School;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class GradeService {

    private final School gerardoInstitute;
    private final School zetCollege;

    @Value("${school.score.limit:100}")
    private int limitGrade;

    public GradeService(@Qualifier("GerardoInstitute") School gerardoInstitute, @Qualifier("ZetCollege") School zetCollege){
        this.gerardoInstitute = gerardoInstitute;
        this.zetCollege = zetCollege;
    }

    public Grade createGrade(String schoolName, Grade grade) {
        if("GerardoInstitute".equalsIgnoreCase(schoolName) && grade.getScore() <= limitGrade){
            gerardoInstitute.getGrades().put(grade.getId(), grade);
            log.info("The grade was successfully registered in Gerardo Institute {}",grade);
        }else if("ZetCollege".equalsIgnoreCase(schoolName) && grade.getScore() <= limitGrade) {
            zetCollege.getGrades().put(grade.getId(), grade);
            log.info("The grade was successfully registered in Zet College {}",grade);
        }
        return grade;
    }

    public List<String> getGradesByStudent(String schoolName, Integer id){
        List<String> studentGrades = new ArrayList<>();
        if("GerardoInstitute".equalsIgnoreCase(schoolName)){
            for(Grade grade: gerardoInstitute.getGrades().values()) {
                if (grade.getStudent().getId() == id) {
                    studentGrades.add("Name: " + grade.getStudent().getFirstName() + " " + grade.getStudent().getLastName());
                    studentGrades.add("Age: " + grade.getStudent().getAge());
                    studentGrades.add("Professor: " + grade.getCourse().getProfessorName());
                    studentGrades.add("Curse: " + grade.getCourse().getNameCourse());
                    studentGrades.add("Score: " + grade.getScore());
                }
            }
        }else if("ZetCollege".equalsIgnoreCase(schoolName)) {
            for(Grade grade: zetCollege.getGrades().values()) {
                if (grade.getStudent().getId() == id) {
                    studentGrades.add("Name: " + grade.getStudent().getFirstName() + " " + grade.getStudent().getLastName());
                    studentGrades.add("Age: " + grade.getStudent().getAge());
                    studentGrades.add("Professor: " + grade.getCourse().getProfessorName());
                    studentGrades.add("Curse: " + grade.getCourse().getNameCourse());
                    studentGrades.add("Score: " + grade.getScore());
                }
            }
        }
        return studentGrades;
    }

    public boolean deleteGradeByStudentId(String schoolName, int studentId){
        boolean removed = false;

        if("GerardoInstitute".equalsIgnoreCase(schoolName)){
            for(int key : new ArrayList<>(gerardoInstitute.getGrades().keySet())){
                if(gerardoInstitute.getGrades().get(key).getStudent().getId() == studentId){
                    gerardoInstitute.getGrades().remove(key);
                    log.info("Student's grades were successfully deleted in Gerardo Institute {}",studentId);
                    removed = true;
                }
            }
        }else if("ZetCollege".equalsIgnoreCase(schoolName)) {
            for(int key : new ArrayList<>(zetCollege.getGrades().keySet())){
                if(zetCollege.getGrades().get(key).getStudent().getId() == studentId){
                    zetCollege.getGrades().remove(key);
                    log.info("Student's grades were successfully deleted in Zet Collage {}",studentId);
                    removed = true;
                }
            }
        }
        return removed;
    }

    public boolean deleteGradeByCourseId(String schoolName, int courseId){
        boolean removed = false;

        if("GerardoInstitute".equalsIgnoreCase(schoolName)){
            for(int key : new ArrayList<>(gerardoInstitute.getGrades().keySet())){
                if(gerardoInstitute.getGrades().get(key).getCourse().getId() == courseId){
                    gerardoInstitute.getGrades().remove(key);
                    log.info("Course grades were successfully deleted in Gerardo Intitute {}",courseId);
                    removed = true;
                }
            }
        }else if("ZetCollege".equalsIgnoreCase(schoolName)) {
            for(int key : new ArrayList<>(zetCollege.getGrades().keySet())){
                if(zetCollege.getGrades().get(key).getCourse().getId() == courseId){
                    zetCollege.getGrades().remove(key);
                    log.info("Course grades were successfully deleted in Zet College {}",courseId);
                    removed = true;
                }
            }
        }
        return removed;
    }
}