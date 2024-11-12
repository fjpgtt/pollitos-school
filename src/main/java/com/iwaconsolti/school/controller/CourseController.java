package com.iwaconsolti.school.controller;

import com.iwaconsolti.school.model.Course;
import com.iwaconsolti.school.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/{schoolName}/cours")
public class CourseController {

    @Autowired
    @Qualifier("gerardoServicePopulated")
    private SchoolService gerardoService;

    @Autowired
    @Qualifier("zetServicePopulated")
    private SchoolService zetService;

    private SchoolService getServiceBySchoolName(String schoolName) {
        if ("GerardoInstitute".equalsIgnoreCase(schoolName)) {
            return gerardoService;
        } else if ("ZetCollege".equalsIgnoreCase(schoolName)) {
            return zetService;
        } else {
            throw new IllegalArgumentException("Invalid school name");
        }
    }

    @PostMapping
    public void createCourse(@PathVariable String schoolName, @RequestBody Course course) {
        getServiceBySchoolName(schoolName).addCourse(course);
    }

    @GetMapping
    public List<Course> getAllCourses(@PathVariable String schoolName) {
        return getServiceBySchoolName(schoolName).getAllCourses();
    }

    @PutMapping("/{id}")
    public void editCourse(@PathVariable String schoolName, @PathVariable int id, @RequestBody Course updatedCourse) {
        getServiceBySchoolName(schoolName).editCourse(id, updatedCourse);
    }
}
