// File: AdminDashboardViewController.java
package com.example.lms.controller.admin;

import com.example.lms.dto.dashboard.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/dashboard/admin")
public class AdminDashboardViewController {

    @GetMapping
    public String viewAdminDashboard(Model model) {
        AdminDashboardResponse dashboard = new AdminDashboardResponse(
                150,
                18,
                3,
                30,
                40,
                List.of(
                        new UserStatusItem("Nguyễn Văn B", "Student", "Created", LocalDateTime.now().minusDays(1)),
                        new UserStatusItem("Trần Thị C", "Teacher", "Locked", LocalDateTime.now().minusHours(3))
                ),
                List.of(
                        new ActivityLogItem("Admin A", "Tạo khoá học Spring", LocalDateTime.now().minusHours(2)),
                        new ActivityLogItem("Admin B", "Khoá tài khoản Trần Thị C", LocalDateTime.now().minusDays(1))
                )
        );
        model.addAttribute("dashboard", dashboard);
        return "dashboard/admin-dashboard";
    }
}
