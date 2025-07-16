// File: TeacherDashboardViewController.java
package com.example.lms.controller.teacher;

import com.example.lms.dto.dashboard.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/dashboard/teacher")
public class TeacherDashboardViewController {

    @GetMapping("/{teacherId}")
    public String viewTeacherDashboard(@PathVariable Long teacherId, Model model) {
        TeacherDashboardResponse dashboard = new TeacherDashboardResponse(
                2,
                6,
                List.of(
                        new ClassProgressItem("Lớp Spring Boot", 70),
                        new ClassProgressItem("Lớp Cơ sở dữ liệu", 55)
                ),
                List.of(
                        new StudentAlertItem("Lê Văn T", "Spring Boot", 40),
                        new StudentAlertItem("Ngô Thị M", "CSDL", 35)
                ),
                List.of(
                        new AssignmentItem("Essay 3", "Chưa chấm", null),
                        new AssignmentItem("Quiz 5", "Chưa có học sinh nộp", null)
                )
        );
        model.addAttribute("dashboard", dashboard);
        return "dashboard/teacher-dashboard";
    }
}
