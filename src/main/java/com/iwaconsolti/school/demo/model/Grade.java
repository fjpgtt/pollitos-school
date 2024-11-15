package com.iwaconsolti.school.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Grade {
    private static final int MAX_SCORE = 100;
    private int score;
    private int student;
    private int course;

    public int getMaxScore() {
        return Integer.parseInt(System.getProperty("grade.max.score", String.valueOf(MAX_SCORE)));
    }
}
