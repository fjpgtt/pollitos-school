package com.iwaconsolti.school.miguel.service;

import com.iwaconsolti.school.miguel.model.Courses;
import com.iwaconsolti.school.miguel.model.Qualifications;
import com.iwaconsolti.school.miguel.model.Students;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

@Service
public class SchoolService {
    private Map<String, Students> newstudents = new HashMap<>();
    private Map<String, Courses> newcourse = new HashMap<>();
    private Map<String, Qualifications> newQualification = new HashMap<>();

    public Students createrStudents(Students students) {
        newstudents.put(students.getId(), students);
        return students;
    }

    public Courses createCourse(Courses course) {
        newcourse.put(course.getId(), course);
        return course;
    }

    public Qualifications createQualification(Qualifications qualification) {
        return qualification;
    }

    public List<Students> getStudents() {
        return new ArrayList<>(newstudents.values());
    }

    public List<Courses> getCourses(){
        return new ArrayList<>(newcourse.values());
    }





}
