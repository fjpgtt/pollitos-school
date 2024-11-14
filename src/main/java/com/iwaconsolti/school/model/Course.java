package com.iwaconsolti.school.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Course {
    private final int id;
    private String name;
    private String professorName;
}
