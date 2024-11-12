package com.iwaconsolti.school.demo.service;

import com.iwaconsolti.school.demo.model.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class SchoolService {
    private final School gerardoInstitute;
    private final School zetCollege;

    @Autowired
    public SchoolService(@Qualifier("gerardoInstitute") School gerardoInstitute, @Qualifier("zetCollege") School zetCollege) {
        this.gerardoInstitute = gerardoInstitute;
        this.zetCollege = zetCollege;
    }

    public School getSchoolByName(String schoolName) {
        if ("GerardoInstitute".equalsIgnoreCase(schoolName)) {
            return gerardoInstitute;
        } else if ("ZetCollege".equalsIgnoreCase(schoolName)) {
            return zetCollege;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "School not found");
        }
    }

}
