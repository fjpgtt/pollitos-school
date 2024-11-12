package com.iwaconsolti.school.miguel.model;

import lombok.Data;

@Data
public class Grade {
    private int id;
    private long score;
    private Students student;
    private Courses course;

}
