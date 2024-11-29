package com.iwaconsolti.school.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Table(name = "courses")
@Entity(name = "courses")
public class Course {

    public Course(int id, String name, String professorName) {
        this.id = id;
        this.name = name;
        this.professorName = professorName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="name")
    private String name;
    @Column(name="professor_Name")
    private String professorName;
    @Column (name="school_id")
    int schoolId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id", updatable = false, nullable = false, insertable=false)
    private School school;

    public Course(String name, String professorName, int schoolId) {
        this.name = name;
        this.professorName = professorName;
        this.schoolId = schoolId;
    }
}
