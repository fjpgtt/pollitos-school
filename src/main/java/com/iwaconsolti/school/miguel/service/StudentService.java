package com.iwaconsolti.school.miguel.service;

import com.iwaconsolti.school.miguel.model.School;
import com.iwaconsolti.school.miguel.model.Students;
import com.iwaconsolti.school.miguel.model.dto.StudentsDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Slf4j
@Service
public class StudentService {

    private final School gerardoInstitute;
    private final School zetCollege;

    public StudentService(@Qualifier("GerardoInstitute") School gerardoInstitute, @Qualifier("ZetCollege") School zetCollege){
        this.gerardoInstitute = gerardoInstitute;
        this.zetCollege = zetCollege;
    }

    public Students createStudents(String schoolName, StudentsDTO studentDTO) {
        Students student = new Students(studentDTO);
        if("GerardoInstitute".equalsIgnoreCase(schoolName)){
            if(gerardoInstitute.getStudents().containsKey(student.getId())){
                return gerardoInstitute.getStudents().get(student.getId());
            }
            gerardoInstitute.getStudents().put(student.getId(), student);
        }else if("ZetCollege".equalsIgnoreCase(schoolName)){
            if(zetCollege.getStudents().containsKey(student.getId())){
                return zetCollege.getStudents().get(student.getId());
            }
            zetCollege.getStudents().put(student.getId(), student);
        }
        return student;
    }

    public StudentsDTO getStudentById(String schoolName, int studentId){

        if("GerardoInstitute".equalsIgnoreCase(schoolName)){

            Students students = gerardoInstitute.getStudents().get(studentId);
            if(students != null){
                return new StudentsDTO(students);
            }
        }else if("ZetCollege".equalsIgnoreCase(schoolName)) {

            Students students = zetCollege.getStudents().get(studentId);
            if(students != null){
                return new StudentsDTO(students);
            }
        }
        return null;
    }

    public Collection<StudentsDTO> getStudents(String schoolName) {

        Collection<StudentsDTO> studentsDTO = new ArrayList<>();

        if("GerardoInstitute".equalsIgnoreCase(schoolName)){
            for(Students students : gerardoInstitute.getStudents().values()){
                StudentsDTO studentDto = new StudentsDTO(students);
                studentsDTO.add(studentDto);
            }
        }else if("ZetCollege".equalsIgnoreCase(schoolName)) {
            for(Students students : gerardoInstitute.getStudents().values()){
                StudentsDTO studentDto = new StudentsDTO(students);
                studentsDTO.add(studentDto);
            }
        }
        return studentsDTO;
    }

    public Students editStudent(int id, String schoolName, StudentsDTO studentDTO){
        Students student = new Students(studentDTO);

        if("GerardoInstitute".equalsIgnoreCase(schoolName)){
            gerardoInstitute.getStudents().put(id,student);
        }else if("ZetCollege".equalsIgnoreCase(schoolName)) {
            zetCollege.getStudents().put(id,student);
        }
        return student;
    }
}