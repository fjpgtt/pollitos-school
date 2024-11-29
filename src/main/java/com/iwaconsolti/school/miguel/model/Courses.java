package com.iwaconsolti.school.miguel.model;

import com.iwaconsolti.school.miguel.model.dto.CoursesDTO;
import lombok.Data;

@Data
public class Courses {
    private int id;
    private String nameCourse;
    private String professorName;

    public Courses(){}

    public Courses(CoursesDTO coursesDTO){
        this.id = coursesDTO.getId();
        this.nameCourse = coursesDTO.getNameCourse();
        this.professorName = coursesDTO.getProfessorName();
    }
}