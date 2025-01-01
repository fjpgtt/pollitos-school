package com.iwaconsolti.school.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class School {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "school", fetch = FetchType.LAZY)
    private List<Student> students;

    @OneToMany(mappedBy = "school", fetch = FetchType.LAZY)
    private List<Course> courses;

    @OneToMany(mappedBy = "school", fetch = FetchType.LAZY)
    private List<Grade> grades;

    public School(String name) {
        this.name = name;
    }

}

