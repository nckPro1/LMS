package com.example.lms.dto.schedule;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ScheduleResponse {
    private Long id;
    private Long classroomId;
    private String dayOfWeek;
    private String startTime;
    private String endTime;
}
