package com.iwaconsolti.school.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Component
public class School{
    private final List<Student> studentsList = new ArrayList<>();
    private final List<Course> courseList = new ArrayList<>();
    private final List<Grade> gradeList = new ArrayList<>();
}
