package com.iwaconsolti.school.miguel.service;

import com.iwaconsolti.school.miguel.model.Courses;
import com.iwaconsolti.school.miguel.model.Grade;
import com.iwaconsolti.school.miguel.model.School;
import com.iwaconsolti.school.miguel.model.Students;
import com.iwaconsolti.school.miguel.model.dto.GradeStudentDTO;
import com.iwaconsolti.school.miguel.model.dto.GradesDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    private List<GradeStudentDTO> gradeStudent(Map<Integer,Grade> grades, int id){
        List<GradeStudentDTO> gradeStudent = new ArrayList<>();

        for(Grade grade : grades.values()){
            if(grade.getStudent().getId() == id){
                GradeStudentDTO gradeStudentDTO = new GradeStudentDTO(
                        grade.getStudent().getFirstName() + " " + grade.getStudent().getLastName(),
                        grade.getStudent().getAge(),
                        grade.getCourse().getProfessorName(),
                        grade.getCourse().getNameCourse(),
                        grade.getScore()
                );
                gradeStudent.add(gradeStudentDTO);
            }
        }
        return gradeStudent;
    }

    public Grade createGrade(String schoolName, GradesDTO gradeDTO) {
        Grade grade = new Grade();
        grade.setId(gradeDTO.getId());
        grade.setScore(gradeDTO.getScore());

        Students student = findStudentById(schoolName, gradeDTO.getStudentId());
        Courses course = findCourseById(schoolName,gradeDTO.getCourseId());

        if("GerardoInstitute".equalsIgnoreCase(schoolName) && grade.getScore() <= limitGrade){
            grade.setStudent(student);
            grade.setCourse(course);

            gerardoInstitute.getGrades().put(grade.getId(), grade);
        }else if("ZetCollege".equalsIgnoreCase(schoolName) && grade.getScore() <= limitGrade) {
            grade.setStudent(student);
            grade.setCourse(course);

            zetCollege.getGrades().put(grade.getId(), grade);
        }
        return grade;
    }

    public List<GradeStudentDTO> getGradesByStudentId(String schoolName, Integer id){
        List<GradeStudentDTO> studentGrades = new ArrayList<>();

        if("GerardoInstitute".equalsIgnoreCase(schoolName)){
            studentGrades.addAll(gradeStudent(gerardoInstitute.getGrades(),id));
        } else if ("ZetCollege".equalsIgnoreCase(schoolName)) {
            studentGrades.addAll(gradeStudent(zetCollege.getGrades(),id));
        }
        return studentGrades;
    }

    public boolean deleteGradeByStudentId(String schoolName, int studentId){
        boolean removed = false;

        if("GerardoInstitute".equalsIgnoreCase(schoolName)){
            for(Map.Entry<Integer, Grade> entry: gerardoInstitute.getGrades().entrySet()){
                if(entry.getValue().getStudent().getId() == studentId){
                    gerardoInstitute.getGrades().remove(entry.getKey());
                    removed = true;
                }
            }

        }else if("ZetCollege".equalsIgnoreCase(schoolName)) {
            for(Map.Entry<Integer, Grade> entry: zetCollege.getGrades().entrySet()){
                if(entry.getValue().getStudent().getId() == studentId){
                    zetCollege.getGrades().remove(entry.getKey());
                    removed = true;
                }
            }
        }
        return removed;
    }

    public boolean deleteGradeByCourseId(String schoolName, int courseId){
        boolean removed = false;

        if("GerardoInstitute".equalsIgnoreCase(schoolName)){
            for(Map.Entry<Integer, Grade> entry: gerardoInstitute.getGrades().entrySet()){
                if(entry.getValue().getCourse().getId() == courseId){
                    gerardoInstitute.getGrades().remove(entry.getKey());
                }
            }
        }else if("ZetCollege".equalsIgnoreCase(schoolName)) {
            for(Map.Entry<Integer, Grade> entry: zetCollege.getGrades().entrySet()){
                if(entry.getValue().getCourse().getId() == courseId){
                    zetCollege.getGrades().remove(entry.getKey());
                    removed = true;
                }
            }
        }
        return removed;
    }

    private Students findStudentById(String schoolName, int studentId) {
        if ("GerardoInstitute".equals(schoolName)) {
            return gerardoInstitute.getStudents().get(studentId);
        } else if ("ZetCollege".equals(schoolName)) {
            return zetCollege.getStudents().get(studentId);
        }
        return null;
    }

    private Courses findCourseById(String schoolName, int courseId) {
        if ("GerardoInstitute".equals(schoolName)) {
            return gerardoInstitute.getCourses().get(courseId);
        } else if ("ZetCollege".equals(schoolName)) {
            return zetCollege.getCourses().get(courseId);
        }
        return null;
    }

}