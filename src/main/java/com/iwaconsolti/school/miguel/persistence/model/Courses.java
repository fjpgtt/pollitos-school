package com.iwaconsolti.school.miguel.persistence.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Courses {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name_course")
    private String nameCourse;

    @Column(name = "professor_name")
    private String professorName;

    @Column(name = "school_id")
    private int schoolId;
}