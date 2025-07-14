package com.example.lms.dto.schedule;

import lombok.Data;

@Data
public class AttendanceDTO {
    private Long userId;
    private String status; // PRESENT, ABSENT, LATE
}
