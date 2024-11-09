package com.iwaconsolti.school.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Data
@AllArgsConstructor
@Component
public class School{
    private final List<Student> studentsList;
    private final List<Course> courseList;
    private final List<Grade> gradeList;

    public School() {
        this.studentsList = new ArrayList<>();
        this.courseList = new ArrayList<>();
        this.gradeList = new ArrayList<>();
    }


}
