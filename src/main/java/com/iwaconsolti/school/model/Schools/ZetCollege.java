package com.iwaconsolti.school.model.Schools;

import com.iwaconsolti.school.model.School;

import java.util.ArrayList;

public class ZetCollege extends School {
    public ZetCollege() {
        this.name = "ZetCollege";
        this.students = new ArrayList<>();
        this.courses = new ArrayList<>();
        this.grades = new ArrayList<>();
    }
}
