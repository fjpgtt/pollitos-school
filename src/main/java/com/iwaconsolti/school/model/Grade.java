package com.iwaconsolti.school.model;

import lombok.*;

import java.util.List;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Grade {
    private Integer id;
    private Integer score;
    private Student student;
    private Course course;
}
