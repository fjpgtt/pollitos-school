package com.iwaconsolti.school.demo.controller;

import com.iwaconsolti.school.demo.model.Course;
import com.iwaconsolti.school.demo.model.School;
import com.iwaconsolti.school.demo.service.CourseService;
import com.iwaconsolti.school.demo.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/school")
public class CourseController {
    CourseService courseService;
    SchoolService schoolService;

    @Autowired
    public CourseController(CourseService courseService, SchoolService schoolService) {
        this.courseService = courseService;
        this.schoolService = schoolService;
    }

    @DeleteMapping("/{schoolName}/courseGradesDelete")
    public String deleteGradesStudent(
            @PathVariable String schoolName,
            @RequestParam int id){

        School school = schoolService.getSchoolByName(schoolName); // Obtener la escuela según el nombre
        return CourseService.deleteGradesCourse(school, id);
    }

    @PutMapping("/{schoolName}/course")
    public String updateCourse(
            @PathVariable String schoolName,
            @RequestParam int id,
            @RequestParam String name,
            @RequestParam String professorName){

        School school = schoolService.getSchoolByName(schoolName);
        Course course = new Course(id, name, professorName);
        return courseService.updateCourse(school, course);
    }

    @PostMapping("/{schoolName}/courses")
    public String createCourse(
            @PathVariable String schoolName,
            @RequestParam int id,
            @RequestParam String name,
            @RequestParam String professorName){

        School school = schoolService.getSchoolByName(schoolName);
        Course course = new Course(id, name, professorName);
        return courseService.createCourse(school, course);
    }

    @GetMapping("/{schoolName}/getCourses")
    public String returnCourses(@PathVariable String schoolName){
        return courseService.getCourses(schoolName);
    }
}
