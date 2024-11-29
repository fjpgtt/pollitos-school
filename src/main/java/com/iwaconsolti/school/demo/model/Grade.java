package com.iwaconsolti.school.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Table(name = "grades")
@Entity(name = "grades")
public class Grade {
    private static final int MAX_SCORE = 100;

    public Grade(int studentId, int courseId, int score) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.score = score;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="score")
    private int score;
    @Column (name="student_id")
    private  int studentId;
    @Column (name="course_id")
    private  int courseId;

    @ManyToOne
    @JoinColumn(name = "student_id", updatable = false, nullable = false, insertable=false) //Column name that references Student
    private Student student; // Field for the relationship with Student

    @ManyToOne
    @JoinColumn(name = "course_id", updatable = false, nullable = false, insertable=false)
    private Course course;

    public int getMaxScore() {
        return Integer.parseInt(System.getProperty("grade.max.score", String.valueOf(MAX_SCORE)));
    }
}
