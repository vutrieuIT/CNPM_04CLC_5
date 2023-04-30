package com.project.schoolmanagement.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GradeDTO {
    private Long student_id;
    private String studentName;

    private Long grade_id;
    private Float grade;
    //constructor, getter, setter
}
