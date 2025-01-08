package com.iwaconsolti.school.miguel.persistence.model;

import com.iwaconsolti.school.miguel.persistence.model.dto.GradesDTO;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private long score;

    @Column(name = "student_id")
    private int studentId;

    @Column(name = "course_id")
    private int courseId;

    public Grade(){}

    public Grade(GradesDTO dto){
        this.score = dto.getScore();
        this.courseId = dto.getCourseId();
        this.studentId = dto.getStudentId();
    }
}