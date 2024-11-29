package com.iwaconsolti.school.miguel.model.dto;

import com.iwaconsolti.school.miguel.model.Courses;
import lombok.Getter;

@Getter
public class CoursesDTO {
    private int id;
    private String nameCourse;
    private String professorName;

    public CoursesDTO(Courses courses){
        this.id = courses.getId();
        this.nameCourse = courses.getNameCourse();
        this.professorName = courses.getProfessorName();
    }
}
