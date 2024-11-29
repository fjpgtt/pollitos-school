package com.iwaconsolti.school.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String professorName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id")
    private School school;

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    private List<Grade> grades;


    public Course(String name, String professorName, School school) {
        this.name = name;
        this.professorName = professorName;
        this.school = school;
    }
}
