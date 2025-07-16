// File: StudentDashboardViewController.java
package com.example.lms.controller.student;

import com.example.lms.dto.dashboard.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/dashboard/student")
public class StudentDashboardViewController {

    @GetMapping("/{studentId}")
    public String viewStudentDashboard(@PathVariable Long studentId, Model model) {
        StudentDashboardResponse dashboard = new StudentDashboardResponse(
                72,
                List.of(
                        new AssignmentItem("Essay 1", "Viết bài luận A", LocalDateTime.now().plusDays(3)),
                        new AssignmentItem("Quiz 2", "Trắc nghiệm chương 2", LocalDateTime.now().plusDays(5))
                ),
                List.of(
                        new NotificationItem("🔔 Lớp học Lập trình Web bắt đầu lúc 8h mai."),
                        new NotificationItem("⏳ Hạn nộp bài Essay 2 là ngày 20/07.")
                ),
                true
        );
        model.addAttribute("dashboard", dashboard);
        return "dashboard/student-dashboard";
    }
}
