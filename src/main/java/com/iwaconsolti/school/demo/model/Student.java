package com.iwaconsolti.school.demo.model;

import jakarta.persistence.*;
import lombok.*;


@Data
@NoArgsConstructor
@Entity(name = "students")
public class Student {

    public Student(int id, String firstName, String lastName, int age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="age")
    private int age;

    @Column (name="school_id")
    int schoolId;

    @ManyToOne
    @JoinColumn(name = "school_id", updatable = false, nullable = false, insertable=false)
    private School school;

    public Student(String firstName, String lastName, int age, int schoolId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.schoolId = schoolId;
    }
}
