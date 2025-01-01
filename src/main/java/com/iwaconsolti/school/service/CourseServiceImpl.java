package com.iwaconsolti.school.service;

import com.iwaconsolti.school.model.Course;
import com.iwaconsolti.school.repository.CourseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public List<Course> findAllBySchoolId(int schoolId) {
        return courseRepository.findAllBySchoolId(schoolId);
    }

    @Override
    public Optional<Course> getCourseById(int id, int schoolId) {
        return courseRepository.findByIdAndSchoolId(id, schoolId);
    }

    @Override
    public Course updateCourse(int id, Course course, int schoolId) {
        if (courseRepository.findByIdAndSchoolId(id, schoolId).isPresent()) {
            course.setId(id);
            return courseRepository.save(course);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}
