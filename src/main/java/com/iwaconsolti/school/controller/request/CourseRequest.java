
package com.iwaconsolti.school.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CourseRequest {
    private final int id;
    private String name;
    private String professorName;
}
