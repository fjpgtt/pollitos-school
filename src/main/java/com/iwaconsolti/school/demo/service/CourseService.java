package com.iwaconsolti.school.demo.service;

import com.iwaconsolti.school.demo.entity.CourseEntity;
import com.iwaconsolti.school.demo.entity.repository.CourseRepository;
import com.iwaconsolti.school.demo.model.Course;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class CourseService implements CourseInterface {

    //H2
    @Autowired
    CourseRepository courseRepository;

    private final List<Course> courses = new ArrayList<>();

    public CourseService() {
        Course mathematics = Course.builder().id(1).name("Matematicas").professorName("Sergio").creationDate(new Date()).build();
        Course Spanish = Course.builder().id(2).name("Español").professorName("Guadalupe").creationDate(new Date()).build();

        this.courses.add(mathematics);
        this.courses.add(Spanish);
    }

    //H2
    @Override
    public List<CourseEntity> findAllCourses(){
        return courseRepository.findAll();
    }

    public List<Course> getCourses() {
        return this.courses;
    }

    public List<Course> findCoursesByName(String name) {
        List<CourseEntity> courses = this.courseRepository.findByName(name);
        return courses.stream().map(courseEntity -> {
            return Course.builder()
                    .name(courseEntity.getName())
                    .professorName(courseEntity.getProfessorName())
                    .id(courseEntity.getId())
                    .build();
        }).toList();
    }

    public Course getById(int id) {
        log.info("Id: {}", id);
        List<Course> courses = this.courses.stream().filter(course -> course.getId() == id).toList();
        log.info("Students: {}", courses);
        if(!courses.isEmpty())
            return courses.getFirst();
        return null;
    }

    public Course createCourse(Course course){
        if(course != null){
            course.setCreationDate(new Date());
            this.courses.add(course);
        }
        return course;
    }

    public Course update(Course course){
        this.courses.stream().forEach(c -> {
          if (c.getId() == course.getId()){
            c.setName(course.getName());
            c.setProfessorName(course.getProfessorName());
          }
        });
        return this.courses.stream().filter(c -> c.getId() == course.getId()).toList().getFirst();
    }
}
