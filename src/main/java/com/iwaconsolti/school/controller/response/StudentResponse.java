package com.iwaconsolti.school.controller.response;

import com.iwaconsolti.school.model.School;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudentResponse {
    private final int id;
    private String firstName;
    private String lastName;
    private int age;
}
