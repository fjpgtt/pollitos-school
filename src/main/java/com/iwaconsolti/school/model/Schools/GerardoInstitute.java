package com.iwaconsolti.school.model.Schools;

import com.iwaconsolti.school.model.School;

import java.util.ArrayList;

public class GerardoInstitute extends School {
    public GerardoInstitute() {
        this.name = "GerardoInstitute";
        this.students = new ArrayList<>();
        this.courses = new ArrayList<>();
        this.grades = new ArrayList<>();
    }
}