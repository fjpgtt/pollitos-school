package com.iwaconsolti.school.miguel.persistence.repository;

import com.iwaconsolti.school.miguel.persistence.model.UnionSchoolStudents;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UnionSchoolStudentRepository extends CrudRepository<UnionSchoolStudents, Long> {
    @Override
    UnionSchoolStudents save(UnionSchoolStudents unionSchoolStudents);
}
