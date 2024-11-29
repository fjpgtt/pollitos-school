package com.iwaconsolti.school.controller.request;
import com.iwaconsolti.school.model.School;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GradeRequest {
    private int score;
    private int studentId;
    private int courseId;
    private School school;

}