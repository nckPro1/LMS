package com.example.lms.dto.schedule;

import lombok.Data;

@Data
public class ScheduleRequest {
    private Long classroomId;
    private String dayOfWeek;    // e.g., "MONDAY"
    private String startTime;    // e.g., "08:00"
    private String endTime;      // e.g., "10:00"
}
