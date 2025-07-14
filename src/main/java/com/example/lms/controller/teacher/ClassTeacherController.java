package com.example.lms.controller.teacher;

import com.example.lms.dto.classroom.ClassDetailDTO;
import com.example.lms.dto.schedule.AttendanceDTO;
import com.example.lms.service.teacher.ClassTeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher/classes")
@RequiredArgsConstructor
public class ClassTeacherController {

    private final ClassTeacherService classTeacherService;

    // Xem các lớp mà giáo viên đang dạy
    @GetMapping
    public ResponseEntity<List<ClassDetailDTO>> getMyClasses() {
        return ResponseEntity.ok(classTeacherService.getMyClasses());
    }

    // Xem chi tiết lớp (dùng lại DTO từ admin)
    @GetMapping("/{classId}")
    public ResponseEntity<ClassDetailDTO> getClassDetail(@PathVariable Long classId) {
        return ResponseEntity.ok(classTeacherService.getClassDetail(classId));
    }

    // Gửi điểm danh
    @PostMapping("/{scheduleId}/attendance")
    public ResponseEntity<Void> submitAttendance(@PathVariable Long scheduleId,
                                                 @RequestBody List<AttendanceDTO> attendanceList) {
        classTeacherService.submitAttendance(scheduleId, attendanceList);
        return ResponseEntity.ok().build();
    }

    // (Tuỳ chọn) Tham gia lớp học online
    @GetMapping("/{classId}/join")
    public ResponseEntity<String> joinOnlineClass(@PathVariable Long classId) {
        return ResponseEntity.ok("https://example.com/classroom/" + classId);
    }
}
