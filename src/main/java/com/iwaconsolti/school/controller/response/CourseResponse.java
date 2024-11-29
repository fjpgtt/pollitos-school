
package com.iwaconsolti.school.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CourseResponse {
    private final int id;
    private String name;
    private String professorName;
}
