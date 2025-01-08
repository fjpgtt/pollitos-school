package com.iwaconsolti.school.miguel.persistence.model;

import com.iwaconsolti.school.miguel.persistence.model.dto.CoursesDTO;
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

    public Courses(){}

    public Courses(CoursesDTO coursesDTO){
        this.nameCourse = coursesDTO.getNameCourse();
        this.professorName = coursesDTO.getProfessorName();
    }
}