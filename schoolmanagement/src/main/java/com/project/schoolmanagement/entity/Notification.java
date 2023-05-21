package com.project.schoolmanagement.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "notification")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notificationId;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "cre_date")
    @Temporal(TemporalType.DATE)
    private Date creDate;

    @ManyToOne(targetEntity = Assign.class, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "assign_id", referencedColumnName = "assign_id", foreignKey = @ForeignKey(name = "FK_assign_notification"))
    private Assign assign;

    // auto set date
    @PrePersist
    public void setCreDate() {
        this.creDate = new Date(System.currentTimeMillis());
    }
}