package com.iwaconsolti.school.miguel.service;

import com.iwaconsolti.school.miguel.model.Courses;
import com.iwaconsolti.school.miguel.model.Grade;
import com.iwaconsolti.school.miguel.model.School;
import com.iwaconsolti.school.miguel.model.Students;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;


@Slf4j
@Service
public class SchoolService {
    private final Map<String, School> schools = new HashMap<>();

    @Value("${school.score.limit:100}")
    private int limitGrade;

    public SchoolService(School gerardoInstitute, School zetCollege){
        schools.put(gerardoInstitute.getName(),gerardoInstitute);
        schools.put(zetCollege.getName(),zetCollege);
    }

    public School getSchool(String schoolName) {
        return schools.get(schoolName);
    }

    public Students createrStudents(String schoolName, Students student) {
        School school = getSchool(schoolName);
        school.getStudents().put(student.getId(), student);
        log.info("The student registered successfully {}",student);
        return student;
    }

    public Collection<Students> getStudents(String schoolName) {
        School school = getSchool(schoolName);
        return new ArrayList<>(school.getStudents().values());
    }

    public Courses createCourse(String schoolName,Courses course) {
        School school = getSchool(schoolName);
        school.getCourses().put(course.getId(), course);
        log.info("The course was successfully registered {}",course);
        return course;
    }

    public Collection<Courses> getCourses(String schoolName){
        School school = getSchool(schoolName);
        return school.getCourses().values();
    }

    public Grade createGrade(String schoolName,Grade grade) {
        School school = getSchool(schoolName);

        if(grade.getScore() <= limitGrade){
            school.getGrades().put(grade.getId(), grade);
            log.info("The grade was successfully registered {}",grade);
            return grade;
        }else{
            log.error("The rating provided exceeds the allowed limit.");
        }
        return null;
    }

    public List<String> getGradesByStudent(String schoolName,Integer id){
        School school = getSchool(schoolName);
        List<String> studentGrades = new ArrayList<>();
        for(Grade grade: school.getGrades().values()){
            if(grade.getStudent().getId() == id){
                studentGrades.add("Name: " + grade.getStudent().getFirstName()+ " " + grade.getStudent().getLastName());
                studentGrades.add("Age: " + grade.getStudent().getAge());
                studentGrades.add("Professor: " + grade.getCourse().getProfessorName());
                studentGrades.add("Curse: " + grade.getCourse().getNameCourse());
                studentGrades.add("Score: " + grade.getScore());
            }
        }
        return studentGrades;
    }

    public Courses editCourse(int id, String schoolName, Courses course){
        School school = getSchool(schoolName);
        if(school.getCourses().containsKey(id)){
            course.setId(id);
            school.getCourses().put(id,course);
            log.info("The course was edited successfully {}",course);
            return course;
        }
        return course;
    }

    public Students editStudent(int id, String schoolName, Students student){
        School school = getSchool(schoolName);
        if(school.getStudents().containsKey(id)){
            student.setId(id);
            school.getStudents().put(id,student);
            log.info("The student was edited successfully {}",student);
            return student;
        }
        return student;
    }

    public boolean deleteGradeByStudentId(String schoolname, int studentId){
        School school = getSchool(schoolname);
        boolean removed = false;

        for(int key : new ArrayList<>(school.getGrades().keySet())){
            if(school.getGrades().get(key).getStudent().getId() == studentId){
                school.getGrades().remove(key);
                log.info("Student's grades were successfully deleted {}",studentId);
                removed = true;
            }
        }

        return removed;
    }

    public boolean deleteGradeByCourseId(String schoolName, int courseId){
        School school = getSchool(schoolName);
        boolean removed = false;

        for(int key : new ArrayList<>(school.getGrades().keySet())){
            if(school.getGrades().get(key).getCourse().getId() == courseId){
                school.getGrades().remove(key);
                log.info("Course grades were successfully deleted {}",courseId);
                removed = true;
            }
        }

        return removed;
    }
}