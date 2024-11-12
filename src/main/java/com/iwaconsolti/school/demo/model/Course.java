package com.iwaconsolti.school.demo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Setter
@Getter
@ToString
@Component
public class Course {

    private int id;
    private String nameCourse, professorName;

}
