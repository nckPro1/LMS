// File: AdminDashboardController.java
package com.example.lms.controller.admin;

import com.example.lms.dto.dashboard.*;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/dashboard/admin")
public class AdminDashboardController {

    @GetMapping
    public AdminDashboardResponse getDashboard() {
        return new AdminDashboardResponse(
                120, // Students
                12,  // Teachers
                3,   // Admins
                25,  // Courses
                40,  // Classes
                List.of(
                        new UserStatusItem("Nguyễn Văn C", "Student", "Created", LocalDateTime.now().minusDays(1)),
                        new UserStatusItem("Phạm Thị D", "Teacher", "Locked", LocalDateTime.now().minusHours(6))
                ),
                List.of(
                        new ActivityLogItem("Admin A", "Reset password for user Nguyễn Văn C", LocalDateTime.now().minusHours(2)),
                        new ActivityLogItem("Admin B", "Created new course: Java OOP", LocalDateTime.now().minusDays(1))
                )
        );
    }
}
