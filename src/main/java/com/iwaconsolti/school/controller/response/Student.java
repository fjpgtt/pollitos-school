package com.iwaconsolti.school.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Student {
    private final int id;
    private String firstName;
    private String lastName;
    private Integer age;
}
