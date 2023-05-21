package com.project.schoolmanagement.entity;

import java.sql.Date;
import java.util.List;

import com.project.schoolmanagement.entity.Student.Gender;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "teacher", uniqueConstraints = {
        @UniqueConstraint(name = "UQ_Teacher_email", columnNames = "email"),
        @UniqueConstraint(name = "UQ_Teacher_phone", columnNames = "phone"),
        @UniqueConstraint(name = "UQ_Teacher_username", columnNames = "username")
})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teacher_id;

    @Column(name = "name")
    private String name;

    @Column(name = "date_of_birth")
    private Date dob;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "address")
    private String address;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    //relationship with Class entity
    @OneToOne(mappedBy = "teacher", fetch = FetchType.LAZY)
    private Class clazz;

    //relationship with Subject entity
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id", referencedColumnName = "subject_id", foreignKey = @ForeignKey(name = "FK_subjects_teachers"))
    private Subject subject;

    @OneToMany(mappedBy = "teacher", targetEntity = Assign.class)
    private List<Assign> assigns;
}

/*
 * The 'mappedBy' attribute of the @ManyToMany annotation is used to specify the
 * name of the inverse relationship in the Teacher entity.
 * In this case, the Subject entity is the owner of the relationship, and the
 * Teacher entity is the inverse side of the relationship.
 * The 'mappedBy' attribute tells Hibernate, the ORM framework used by Spring
 * Boot, to look for a property named "subjects" in the Teacher entity to map
 * the relationship.
 */

/*
 * The Set interface is used to represent a collection of objects that do not
 * contain any duplicates, and the order of the elements is not important.
 * The HashSet implementation of the Set interface is used to store the Teacher
 * entities in this example.
 */