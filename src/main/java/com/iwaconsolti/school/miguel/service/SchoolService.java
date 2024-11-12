package com.iwaconsolti.school.miguel.service;

import com.iwaconsolti.school.miguel.model.Courses;
import com.iwaconsolti.school.miguel.model.Grade;
import com.iwaconsolti.school.miguel.model.School;
import com.iwaconsolti.school.miguel.model.Students;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.*;


@Slf4j
@Service
public class SchoolService {

    private final School gerardoInstitute;
    private final School zetCollege;

    @Value("${school.score.limit:100}")
    private int limitGrade;

    public SchoolService(@Qualifier("GerardoInstitute") School gerardoInstitute, @Qualifier("ZetCollege") School zetCollege){
        this.gerardoInstitute = gerardoInstitute;
        this.zetCollege = zetCollege;
    }

    public Students createrStudents(String schoolName, Students student) {
        if("GerardoInstitute".equals(schoolName)){
            gerardoInstitute.getStudents().put(student.getId(), student);
            log.info("The student registered successfully in Gerardo Institute {}",student);
        }else if("ZetCollege".equals(schoolName)){
            zetCollege.getStudents().put(student.getId(), student);
            log.info("The student registered successfully in Zet College {}",student);
        }
        return student;
    }

    public Collection<Students> getStudents(String schoolName) {
        if("GerardoInstitute".equals(schoolName)){
            return new ArrayList<>(gerardoInstitute.getStudents().values());
        }else if("ZetCollege".equals(schoolName)) {
            return new ArrayList<>(zetCollege.getStudents().values());
        }
       return Collections.emptyList();
    }

    public Courses createCourse(String schoolName,Courses course) {
        if("GerardoInstitute".equals(schoolName)){
            gerardoInstitute.getCourses().put(course.getId(), course);
            log.info("The course was successfully registered in Gerardo Institute {}",course);
        }else if("ZetCollege".equals(schoolName)) {
            zetCollege.getCourses().put(course.getId(), course);
            log.info("The course was successfully registered in Zet College {}",course);
        }
        return course;
    }

    public Collection<Courses> getCourses(String schoolName){
        if("GerardoInstitute".equals(schoolName)){
            return gerardoInstitute.getCourses().values();
        }else if("ZetCollege".equals(schoolName)) {
            return zetCollege.getCourses().values();
        }
        return Collections.emptyList();
    }

    public Grade createGrade(String schoolName,Grade grade) {
        if("GerardoInstitute".equals(schoolName) && grade.getScore() <= limitGrade){
            gerardoInstitute.getGrades().put(grade.getId(), grade);
            log.info("The grade was successfully registered in Gerardo Institute {}",grade);
        }else if("ZetCollege".equals(schoolName) && grade.getScore() <= limitGrade) {
            zetCollege.getGrades().put(grade.getId(), grade);
            log.info("The grade was successfully registered in Zet College {}",grade);
        }
        return grade;
    }

    public List<String> getGradesByStudent(String schoolName,Integer id){
        List<String> studentGrades = new ArrayList<>();
        if("GerardoInstitute".equals(schoolName)){
            for(Grade grade: gerardoInstitute.getGrades().values()) {
                if (grade.getStudent().getId() == id) {
                    studentGrades.add("Name: " + grade.getStudent().getFirstName() + " " + grade.getStudent().getLastName());
                    studentGrades.add("Age: " + grade.getStudent().getAge());
                    studentGrades.add("Professor: " + grade.getCourse().getProfessorName());
                    studentGrades.add("Curse: " + grade.getCourse().getNameCourse());
                    studentGrades.add("Score: " + grade.getScore());
                }
            }
        }else if("ZetCollege".equals(schoolName)) {
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

    public Courses editCourse(int id, String schoolName, Courses course){
        if("GerardoInstitute".equals(schoolName)){
            gerardoInstitute.getCourses().put(id,course);
            log.info("The course was edited successfully in Gerardo Institute {}",course);
        }else if("ZetCollege".equals(schoolName)) {
            zetCollege.getCourses().put(id,course);
            log.info("The course was edited successfully in Zet Collage {}",course);

        }
        return course;
    }

    public Students editStudent(int id, String schoolName, Students student){
        if("GerardoInstitute".equals(schoolName)){
            gerardoInstitute.getStudents().put(id,student);
            log.info("The student was edited successfully in Gerardo Institute {}",student);
        }else if("ZetCollege".equals(schoolName)) {
            zetCollege.getStudents().put(id,student);
            log.info("The student was edited successfully in Zet College {}",student);

        }
        return student;
    }

    public boolean deleteGradeByStudentId(String schoolName, int studentId){
        boolean removed = false;

        if("GerardoInstitute".equals(schoolName)){
            for(int key : new ArrayList<>(gerardoInstitute.getGrades().keySet())){
                if(gerardoInstitute.getGrades().get(key).getStudent().getId() == studentId){
                    gerardoInstitute.getGrades().remove(key);
                    log.info("Student's grades were successfully deleted in Gerardo Institute {}",studentId);
                    removed = true;
                }
            }
        }else if("ZetCollege".equals(schoolName)) {
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

        if("GerardoInstitute".equals(schoolName)){
            for(int key : new ArrayList<>(gerardoInstitute.getGrades().keySet())){
                if(gerardoInstitute.getGrades().get(key).getCourse().getId() == courseId){
                    gerardoInstitute.getGrades().remove(key);
                    log.info("Course grades were successfully deleted in Gerardo Intitute {}",courseId);
                    removed = true;
                }
            }
        }else if("ZetCollege".equals(schoolName)) {
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