package com.iwaconsolti.school.demo.controller;

import com.iwaconsolti.school.demo.model.Course;
import com.iwaconsolti.school.demo.model.Grade;
import com.iwaconsolti.school.demo.service.CourseService;
import com.iwaconsolti.school.demo.service.GradeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
@Slf4j
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;
    private final GradeService gradeService;

    @GetMapping()
    public ResponseEntity<List<Course>> getCourses(){
        log.info("Getting all the courses");
        return ResponseEntity.ok(courseService.getCourses());
    }

    @GetMapping("/{id}/grades")
    public ResponseEntity<List<Grade>> getGradesByCourseId(@PathVariable int id){
        log.info("Getting all the grades for course: {}", id);
        return ResponseEntity.ok(this.gradeService.getGradesByCourseId(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable int id) {
        Course course = this.courseService.getById(id);
        log.info("Id: {}", id);
        log.info("Course: {}", course);
        if (course != null)
            return ResponseEntity.ok(course);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> editCourse(@RequestBody Course course, @PathVariable int id){
        log.info("Editing the course");
        Course found = this.courseService.getById(id);
        if (found == null)
          return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        course.setId(found.getId());
        return ResponseEntity.ok(this.courseService.update(course));
    }
  
    @PostMapping()
    public ResponseEntity<Course> getCourses(@RequestBody Course course){
        log.info("Creating the course");
        course = this.courseService.createCourse(course);
        return ResponseEntity.ok(course);
    }

    @DeleteMapping("/{id}/grades")
    public ResponseEntity<Void> deleteAllGrades(@PathVariable int id){
      // First we need to make sure that the course exists
      this.gradeService.deleteAllByCourseId(id);
      return ResponseEntity.noContent().build();
  }

}
