package com.project.schoolmanagement.entity;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "student", uniqueConstraints = {
        @UniqueConstraint(name = "UQ_Student_email", columnNames = "email"),
        @UniqueConstraint(name = "UQ_Student_phone", columnNames = "phone"),
        @UniqueConstraint(name = "UQ_Student_username", columnNames = "username")
})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Long student_id;

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

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    //relationship with Class entity
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id", referencedColumnName = "class_id", foreignKey = @ForeignKey(name = "FK_classes_students"))
    private Class clazz;

    //relationship with Grade entity
    @OneToMany(mappedBy = "student", fetch = FetchType.EAGER, targetEntity = Grade.class)
    private List<Grade> grades;

    //relationship with Absence entity
    @OneToMany(mappedBy = "student", fetch = FetchType.EAGER, targetEntity = Absence.class)
    @JsonIgnore
    private List<Absence> absences;

    public enum Gender {
        NAM,
        NỮ
    }

}
/*
 * The @Enumerated annotation is used to specify how an enumerated type should
 * be persisted to the database.
 * The EnumType.STRING constant is used to specify that the enum value should be
 * persisted as a String.
 * 
 * @JoinColumn(name = "class_id") specifies (chỉ định,xác định) the column in
 * the Student table that
 * will hold the foreign key to the Class table.
 * 
 * @ManyToOne(fetch = FetchType.LAZY) indicates (cho thấy, cho biết) that the
 * relationship between
 * the entities is a many-to-one association and that the Class entity is the
 * parent of the Student entity.
 * The FetchType.LAZY constant is used to specify that the data should be
 * fetched lazily, which means that the data will be fetched when it is needed.
 * >>improve performance by reducing unnecessary database queries.
 */