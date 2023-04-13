package com.demo.schoolmanagementapp.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "assign")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Assign {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long assign_id;

    @Column(name = "description", nullable = true)
    private String description;

    // relationship with Class entity
    @ManyToOne(targetEntity = Class.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id", referencedColumnName = "class_id")
    private Class clazz;
    // relationship with Subject entity
    @ManyToOne(targetEntity = Subject.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id", referencedColumnName = "subject_id")
    private Subject subject;
    // relationship with Teacher entity
    @ManyToOne(targetEntity = Teacher.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id", referencedColumnName = "teacher_id")
    private Teacher teacher;

    @OneToMany(mappedBy = "assign")
    private List<Schedule> schedules;

}
