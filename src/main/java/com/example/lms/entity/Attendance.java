package com.example.lms.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "attendance")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long scheduleId;
    private Long userId;
    private String status; // PRESENT, ABSENT, LATE

    public Attendance(Long scheduleId, Long userId, String status) {
        this.scheduleId = scheduleId;
        this.userId = userId;
        this.status = status;
    }
}
