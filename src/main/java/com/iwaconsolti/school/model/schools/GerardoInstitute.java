package com.iwaconsolti.school.model.schools;

import com.iwaconsolti.school.model.School;

import java.util.ArrayList;

public class GerardoInstitute extends School {
    public GerardoInstitute() {
        super("GerardoInstitute", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        this.name = "GerardoInstitute";
        this.students = new ArrayList<>();
        this.courses = new ArrayList<>();
        this.grades = new ArrayList<>();
    }
}