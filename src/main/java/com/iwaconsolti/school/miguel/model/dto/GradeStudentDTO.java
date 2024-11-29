package com.iwaconsolti.school.miguel.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class GradeStudentDTO {
    private String studentName;
    private int studentAge;
    private String professorName;
    private String courseName;
    private double score;

}
