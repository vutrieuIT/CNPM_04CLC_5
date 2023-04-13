package com.demo.schoolmanagementapp.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "schedule")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long schedule_id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "startTime")
    private LocalDateTime startTime;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "endTime")
    private LocalDateTime endTime;

    @ManyToOne(targetEntity = Assign.class)
    @JoinColumn(name = "assign_id", referencedColumnName = "assign_id", foreignKey = @ForeignKey(name = "FK_assign_schedule"))
    private Assign assign;
}
