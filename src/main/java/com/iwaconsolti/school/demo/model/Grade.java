package com.iwaconsolti.school.demo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Setter
@Getter
@ToString
@Component
public class Grade {

    private int id;
    private long score;
    private Student student;
    private Course course;

}
