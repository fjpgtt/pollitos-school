package com.iwaconsolti.school.controller;

import com.iwaconsolti.school.controller.response.CourseResponse;
import com.iwaconsolti.school.model.Course;
import com.iwaconsolti.school.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/{schoolName}/course")
public class CourseController {

    @Autowired
    @Qualifier("gerardoService")
    private SchoolService gerardoService;

    @Autowired
    @Qualifier("zetService")
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

    // Convertir Course a CourseResponse
    private CourseResponse convertToCourseResponse(Course course) {
        return new CourseResponse(course.getId(), course.getName(), course.getProfessorName());
    }

    // Obtener todos los cursos, devolviendo una lista de CourseResponse
    @GetMapping
    public List<CourseResponse> getAllCourses(@PathVariable String schoolName) {
        List<Course> courses = getServiceBySchoolName(schoolName).getCourses();
        return courses.stream()
                .map(this::convertToCourseResponse)
                .collect(Collectors.toList());
    }

    // Editar un curso
    @PutMapping("/{id}")
    public void editCourse(@PathVariable String schoolName, @PathVariable int id, @RequestBody Course updatedCourse) {
        getServiceBySchoolName(schoolName).editCourse(id, updatedCourse);
    }
}
