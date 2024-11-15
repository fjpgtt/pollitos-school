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
        Students student = new Students();
        student.setId(studentDTO.getId());
        student.setFirstName(studentDTO.getFirstName());
        student.setLastName(studentDTO.getLastName());
        student.setAge(studentDTO.getAge());

        if("GerardoInstitute".equalsIgnoreCase(schoolName)){
            if(gerardoInstitute.getStudents().containsKey(student.getId())){
                return gerardoInstitute.getStudents().get(student.getId());
            }
            gerardoInstitute.getStudents().put(student.getId(), student);
            log.info("The student registered successfully in Gerardo Institute {}",student);
        }else if("ZetCollege".equalsIgnoreCase(schoolName)){
            zetCollege.getStudents().put(student.getId(), student);
            log.info("The student registered successfully in Zet College {}",student);
        }
        return student;
    }

    public Collection<StudentsDTO> getStudentById(String schoolName, int studentId){

        Collection<StudentsDTO> studentsDTOS = new ArrayList<>();

        if("GerardoInstitute".equalsIgnoreCase(schoolName)){
            for(Students students : gerardoInstitute.getStudents().values()) {
                StudentsDTO studentDto = new StudentsDTO();
                studentDto.setId(students.getId());
                studentDto.setFirstName(students.getFirstName());
                studentDto.setLastName(students.getLastName());
                studentDto.setAge(students.getAge());
                studentsDTOS.add(studentDto);
            }
        }else if("ZetCollege".equalsIgnoreCase(schoolName)) {
            for(Students students : zetCollege.getStudents().values()) {
                StudentsDTO studentDto = new StudentsDTO();
                studentDto.setId(students.getId());
                studentDto.setFirstName(students.getFirstName());
                studentDto.setLastName(students.getLastName());
                studentDto.setAge(students.getAge());
                studentsDTOS.add(studentDto);
            }
        }
        return studentsDTOS;
    }

    public Collection<StudentsDTO> getStudents(String schoolName) {

        Collection<StudentsDTO> studentsDTO = new ArrayList<>();

        if("GerardoInstitute".equalsIgnoreCase(schoolName)){
            for(Students students : gerardoInstitute.getStudents().values()){
                StudentsDTO studentDto = new StudentsDTO();
                studentDto.setId(students.getId());
                studentDto.setFirstName(students.getFirstName());
                studentDto.setLastName(students.getLastName());
                studentDto.setAge(students.getAge());
                studentsDTO.add(studentDto);
            }
        }else if("ZetCollege".equalsIgnoreCase(schoolName)) {
            for(Students students : gerardoInstitute.getStudents().values()){
                StudentsDTO studentDto = new StudentsDTO();
                studentDto.setId(students.getId());
                studentDto.setFirstName(students.getFirstName());
                studentDto.setLastName(students.getLastName());
                studentDto.setAge(students.getAge());
                studentsDTO.add(studentDto);
            }
        }
        return studentsDTO;
    }

    public Students editStudent(int id, String schoolName, StudentsDTO studentDTO){
        Students student = new Students();
        student.setId(studentDTO.getId());
        student.setFirstName(studentDTO.getFirstName());
        student.setLastName(studentDTO.getLastName());
        student.setAge(studentDTO.getAge());

        if("GerardoInstitute".equalsIgnoreCase(schoolName)){
            gerardoInstitute.getStudents().put(id,student);
            log.info("The student was edited successfully in Gerardo Institute {}",student);
        }else if("ZetCollege".equalsIgnoreCase(schoolName)) {
            zetCollege.getStudents().put(id,student);
            log.info("The student was edited successfully in Zet College {}",student);

        }
        return student;
    }
}