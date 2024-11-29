package com.iwaconsolti.school.model.schools;

import com.iwaconsolti.school.model.School;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
public class ZetCollege extends School {
    public ZetCollege(String name) {
        super(name);
    }
}
