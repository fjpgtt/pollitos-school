package com.iwaconsolti.school.miguel.persistence.repository;

import com.iwaconsolti.school.miguel.persistence.model.School;
import com.iwaconsolti.school.miguel.persistence.model.Students;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public interface SchoolRepository extends CrudRepository<School, Long> {
    @Query("SELECT s FROM School s WHERE s.name = ?1")
    School findByName(String schoolName);
}
