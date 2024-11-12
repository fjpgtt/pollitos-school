package com.iwaconsolti.school.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GradeResponse {
    private final int id;
    private int score;
    private Student student;
    private CourseResponse courseResponse;
}
