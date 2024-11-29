package com.iwaconsolti.school.miguel.service;

import com.iwaconsolti.school.miguel.model.Courses;
import com.iwaconsolti.school.miguel.model.School;
import com.iwaconsolti.school.miguel.model.dto.CoursesDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Slf4j
@Service
public class CourseService {

    private final School gerardoInstitute;
    private final School zetCollege;

    public CourseService(@Qualifier("GerardoInstitute") School gerardoInstitute, @Qualifier("ZetCollege") School zetCollege){
        this.gerardoInstitute = gerardoInstitute;
        this.zetCollege = zetCollege;
    }

    public Courses createCourse(String schoolName, CoursesDTO courseDTO) {

        Courses course = new Courses(courseDTO);
        if("GerardoInstitute".equalsIgnoreCase(schoolName)){
            if(gerardoInstitute.getCourses().containsKey(course.getId())){
                return gerardoInstitute.getCourses().get(course.getId());
            }
            gerardoInstitute.getCourses().put(course.getId(), course);
        }else if("ZetCollege".equalsIgnoreCase(schoolName)) {
            if(zetCollege.getCourses().containsKey(course.getId())){
                return zetCollege.getCourses().get(course.getId());
            }
            zetCollege.getCourses().put(course.getId(), course);
        }
        return course;
    }

    public Collection<CoursesDTO> getCourses(String schoolName){

        Collection<CoursesDTO> coursesDTOSList = new ArrayList<>();

        if("GerardoInstitute".equalsIgnoreCase(schoolName)){
            for(Courses courses : gerardoInstitute.getCourses().values()){
                CoursesDTO courseDTO = new CoursesDTO(courses);
                coursesDTOSList.add(courseDTO);
            }
        }else if("ZetCollege".equalsIgnoreCase(schoolName)) {
            for(Courses courses : zetCollege.getCourses().values()){
                CoursesDTO courseDTO = new CoursesDTO(courses);
                coursesDTOSList.add(courseDTO);
            }
        }
        return coursesDTOSList;
    }

    public Courses editCourse(int id, String schoolName, CoursesDTO courseDTO){

        if("GerardoInstitute".equalsIgnoreCase(schoolName)){
            if(gerardoInstitute.getCourses().containsKey(id)){

                Courses course = gerardoInstitute.getCourses().get(id);
                course.setNameCourse(courseDTO.getNameCourse());
                course.setProfessorName(courseDTO.getProfessorName());
                gerardoInstitute.getCourses().put(id,course);

                return course;
            }


        }else if("ZetCollege".equals(schoolName)) {
            if(zetCollege.getCourses().containsKey(id)){

                Courses course = zetCollege.getCourses().get(id);
                course.setNameCourse(courseDTO.getNameCourse());
                course.setProfessorName(courseDTO.getProfessorName());

                zetCollege.getCourses().put(id,course);

                return course;
            }

        }
        return null;
    }
}