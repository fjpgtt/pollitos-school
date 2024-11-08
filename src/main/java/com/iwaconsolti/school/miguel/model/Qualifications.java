package com.iwaconsolti.school.miguel.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Setter
@Getter
@ToString
@Component
public class Qualifications {
    private String id;
    private int score;
    private Students student;
    private Courses course;

}
