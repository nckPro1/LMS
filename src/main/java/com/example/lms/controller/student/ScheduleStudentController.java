package com.example.lms.controller.student;

import com.example.lms.dto.schedule.ScheduleResponse;
import com.example.lms.service.student.ScheduleStudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student/schedules")
@RequiredArgsConstructor
public class ScheduleStudentController {

    private final ScheduleStudentService scheduleStudentService;

    // Lấy tất cả lịch học theo lớp mà học sinh học
    @GetMapping("/class/{classId}")
    public ResponseEntity<List<ScheduleResponse>> getSchedulesByClass(@PathVariable Long classId) {
        return ResponseEntity.ok(scheduleStudentService.getSchedulesByClassId(classId));
    }
}
