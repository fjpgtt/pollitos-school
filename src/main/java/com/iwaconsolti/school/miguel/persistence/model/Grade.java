package com.iwaconsolti.school.miguel.persistence.model;

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
}