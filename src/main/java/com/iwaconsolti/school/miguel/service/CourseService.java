package com.iwaconsolti.school.miguel.service;

import com.iwaconsolti.school.miguel.model.Courses;
import com.iwaconsolti.school.miguel.model.School;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Slf4j
@Service
public class CourseService {

    private final School gerardoInstitute;
    private final School zetCollege;

    public CourseService(@Qualifier("GerardoInstitute") School gerardoInstitute, @Qualifier("ZetCollege") School zetCollege){
        this.gerardoInstitute = gerardoInstitute;
        this.zetCollege = zetCollege;
    }

    public Courses createCourse(String schoolName, Courses course) {
        if("GerardoInstitute".equalsIgnoreCase(schoolName)){
            gerardoInstitute.getCourses().put(course.getId(), course);
            log.info("The course was successfully registered in Gerardo Institute {}",course);
        }else if("ZetCollege".equalsIgnoreCase(schoolName)) {
            zetCollege.getCourses().put(course.getId(), course);
            log.info("The course was successfully registered in Zet College {}",course);
        }
        return course;
    }

    public Collection<Courses> getCourses(String schoolName){
        if("GerardoInstitute".equalsIgnoreCase(schoolName)){
            return gerardoInstitute.getCourses().values();
        }else if("ZetCollege".equalsIgnoreCase(schoolName)) {
            return zetCollege.getCourses().values();
        }
        return Collections.emptyList();
    }

    public Courses editCourse(int id, String schoolName, Courses course){
        if("GerardoInstitute".equals(schoolName)){
            gerardoInstitute.getCourses().put(id,course);
            log.info("The course was edited successfully in Gerardo Institute {}",course);
        }else if("ZetCollege".equals(schoolName)) {
            zetCollege.getCourses().put(id,course);
            log.info("The course was edited successfully in Zet Collage {}",course);

        }
        return course;
    }
}