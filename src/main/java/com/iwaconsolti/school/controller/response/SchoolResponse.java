package com.iwaconsolti.school.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public abstract class SchoolResponse {

    protected String name;
    protected List<Student> students;
    protected List<CourseResponse> cours;
    protected List<GradeResponse> gradeResponses;

}
