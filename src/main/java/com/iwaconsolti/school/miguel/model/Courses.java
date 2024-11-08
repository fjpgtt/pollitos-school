package com.iwaconsolti.school.miguel.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Setter
@Getter
@ToString
@Component
public class Courses {
    private String id;
    private String nameCourse, professorName;
}
