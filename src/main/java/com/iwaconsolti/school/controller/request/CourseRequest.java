
package com.iwaconsolti.school.controller.request;

import com.iwaconsolti.school.model.School;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CourseRequest {
    private String name;
    private String professorName;
    private School school;

}
