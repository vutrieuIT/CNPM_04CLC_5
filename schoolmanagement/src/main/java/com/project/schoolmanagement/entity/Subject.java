package com.project.schoolmanagement.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "subject")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subject_id;
    
    @Column(name = "name")
    private String name;

    //relationship with Teacher entity
    @OneToMany(mappedBy = "subject")
    private List<Teacher> teachers;

    @OneToMany(mappedBy = "subject")
    private List<Grade> grades;

}
