package com.iwaconsolti.school.controller.request;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GradeRequest {
    private final int id;
    private int score;
    private StudentRequest studentRequest;
    private CourseRequest courseRequest;

}