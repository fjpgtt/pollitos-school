package com.iwaconsolti.school.miguel.service;

import com.iwaconsolti.school.miguel.persistence.model.School;
import com.iwaconsolti.school.miguel.persistence.model.Students;
import com.iwaconsolti.school.miguel.persistence.model.UnionSchoolStudents;
import com.iwaconsolti.school.miguel.persistence.repository.SchoolRepository;
import com.iwaconsolti.school.miguel.persistence.repository.StudentRepository;
import com.iwaconsolti.school.miguel.persistence.repository.UnionSchoolStudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private UnionSchoolStudentRepository unionSchoolStudentRepository;

    @Autowired
    private SchoolRepository schoolRepository;

    private final School gerardoInstitute;
    private final School zetCollege;

    public StudentService(@Qualifier("GerardoInstitute") School gerardoInstitute, @Qualifier("ZetCollege") School zetCollege){
        this.gerardoInstitute = gerardoInstitute;
        this.zetCollege = zetCollege;
    }

    public  Students createStudentsDB(String schoolName, Students student){
        School school = schoolRepository.findByName(schoolName);
        Students objStudent = studentRepository.save(student);

        UnionSchoolStudents objUnionSchoolStudents = new UnionSchoolStudents();
        objUnionSchoolStudents.setSchool_id(school.getId());
        objUnionSchoolStudents.setStudent_id(objStudent.getId());

        unionSchoolStudentRepository.save(objUnionSchoolStudents);

        return objStudent;
    }

    public List<Students> getAllStudentsDB(String schoolName){
        School school = schoolRepository.findByName(schoolName);
        return studentRepository.findAllStudents(school.getId());
    }

    public Integer editStudentDB(int id, Students student){
        return studentRepository.UpdateStudent(id,student.getFirstName(),student.getLastName(),student.getAge());
    }

    public List<Students> getStudentById(int id){
        return studentRepository.findById(id);
    }
}