package com.project.smartplanai.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "schedule_history")
public class ScheduleHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "schedule_id", nullable = false)
    private Schedule schedule;

    private LocalDateTime previousStartTime;
    private LocalDateTime previousEndTime;
    private LocalDateTime newStartTime;
    private LocalDateTime newEndTime;

    @ManyToOne
    @JoinColumn(name = "changed_by", nullable = false)
    private User changedBy;

    @Column(nullable = false, updatable = false)
    private Timestamp changedAt = new Timestamp(System.currentTimeMillis());
}
