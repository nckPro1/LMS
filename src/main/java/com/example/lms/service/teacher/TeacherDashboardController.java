// File: TeacherDashboardController.java
package com.example.lms.controller.teacher;

import com.example.lms.dto.dashboard.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dashboard/teacher")
public class TeacherDashboardController {

    @GetMapping("/{teacherId}")
    public TeacherDashboardResponse getDashboard(@PathVariable Long teacherId) {
        return new TeacherDashboardResponse(
                3, // số lớp
                12, // số bài tập đã giao
                List.of(
                        new ClassProgressItem("Lớp Java Web", 78),
                        new ClassProgressItem("Lớp Spring Boot", 65)
                ),
                List.of(
                        new StudentAlertItem("Nguyễn Văn A", "Lớp Java Web", 42),
                        new StudentAlertItem("Trần Thị B", "Lớp Spring Boot", 38)
                ),
                List.of(
                        new AssignmentItem("Bài luận số 3", "Chưa chấm", null),
                        new AssignmentItem("Quiz 4", "10 học sinh chưa nộp", null)
                )
        );
    }
}
