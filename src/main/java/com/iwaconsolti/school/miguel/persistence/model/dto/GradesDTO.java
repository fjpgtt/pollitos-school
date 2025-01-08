package com.iwaconsolti.school.miguel.persistence.model.dto;

import com.iwaconsolti.school.miguel.persistence.model.Grade;
import lombok.Data;

@Data
public class GradesDTO {
    private int id;
    private long score;
    private int studentId;
    private int courseId;

    public  GradesDTO(){}

    public GradesDTO(Grade grade){
        this.id = grade.getId();
        this.score = grade.getScore();
        this.studentId = grade.getStudentId();
        this.courseId = grade.getCourseId();
    }
}
