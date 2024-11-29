package com.iwaconsolti.school.model.schools;

import com.iwaconsolti.school.model.School;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
public class GerardoInstitute extends School {
    public GerardoInstitute(String name) {
        super(name);
    }
}
