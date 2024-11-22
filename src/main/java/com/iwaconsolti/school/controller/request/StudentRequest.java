package com.iwaconsolti.school.controller.request;

import com.iwaconsolti.school.model.School;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudentRequest {
    private int id;
    private String firstName;
    private String lastName;
    private int age;
    private School school;

}
