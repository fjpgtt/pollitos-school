package com.iwaconsolti.school.miguel.persistence.model.dto;

import com.iwaconsolti.school.miguel.persistence.model.Courses;
import lombok.Data;

@Data
public class CoursesDTO {
    private int id;
    private String nameCourse;
    private String professorName;

    public CoursesDTO(){}

    public CoursesDTO(Courses course){
        this.id = course.getId();
        this.nameCourse = course.getNameCourse();
        this.professorName = course.getProfessorName();
    }
}
