package com.iwaconsolti.school.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Grade {
    private int score;
    private int student;
    private int course;
}
