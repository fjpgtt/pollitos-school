package com.iwaconsolti.school.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Grade {
    private final int id;
    private int score;
    private Student student;
    private Course course;
}
