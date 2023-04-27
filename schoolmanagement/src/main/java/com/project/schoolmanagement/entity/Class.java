package com.project.schoolmanagement.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "class")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Class {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long class_id;

    @Column(name = "name", nullable = false)
    private String name;

    //relationship with Student entity
    @OneToMany(mappedBy = "clazz")
    private List<Student> students;

    @OneToOne(optional = true)
    @JoinColumn(name = "teacher_id", referencedColumnName = "teacher_id", foreignKey = @ForeignKey(name = "FK_teachers_classes"))
    private Teacher teacher;

    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "teacher_id")
    // private Teacher teacher;

    // @OneToMany(mappedBy = "clazz")
    // private List<Subject> subjects;

    // map object to entity

    public Class(Object[] res) {
        this.class_id = (Long) res[0];
        this.name = (String) res[1];
    }
}
