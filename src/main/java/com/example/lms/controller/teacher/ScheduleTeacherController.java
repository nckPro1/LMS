package com.example.lms.controller.teacher;

import com.example.lms.dto.schedule.ScheduleResponse;
import com.example.lms.service.teacher.ScheduleTeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher/schedules")
@RequiredArgsConstructor
public class ScheduleTeacherController {

    private final ScheduleTeacherService scheduleTeacherService;

    // Lấy toàn bộ lịch của các lớp giáo viên dạy
    @GetMapping
    public ResponseEntity<List<ScheduleResponse>> getAllMySchedules() {
        return ResponseEntity.ok(scheduleTeacherService.getMySchedules());
    }

    // Lấy lịch theo lớp cụ thể
    @GetMapping("/class/{classId}")
    public ResponseEntity<List<ScheduleResponse>> getSchedulesByClass(@PathVariable Long classId) {
        return ResponseEntity.ok(scheduleTeacherService.getSchedulesByClassId(classId));
    }
}
