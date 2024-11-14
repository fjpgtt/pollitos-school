package com.iwaconsolti.school.model.schools;

import com.iwaconsolti.school.model.School;

import java.util.ArrayList;

public class ZetCollege extends School {
    public ZetCollege() {
        super("ZetCollege", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        this.name = "ZetCollege";
        this.students = new ArrayList<>();
        this.courses = new ArrayList<>();
        this.grades = new ArrayList<>();
    }
}
