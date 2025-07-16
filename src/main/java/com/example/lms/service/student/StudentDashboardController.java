// File: StudentDashboardController.java
package com.example.lms.controller.student;

import com.example.lms.dto.dashboard.StudentDashboardResponse;
import com.example.lms.dto.dashboard.AssignmentItem;
import com.example.lms.dto.dashboard.NotificationItem;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/dashboard/student")
public class StudentDashboardController {

    @GetMapping("/{studentId}")
    public StudentDashboardResponse getDashboard(@PathVariable Long studentId) {
        return new StudentDashboardResponse(
                72, // progress %
                List.of(
                        new AssignmentItem("Essay 1", "Writing topic A", LocalDateTime.now().plusDays(3)),
                        new AssignmentItem("Quiz 2", "Grammar practice", LocalDateTime.now().plusDays(5))
                ),
                List.of(
                        new NotificationItem("Lớp học Lập trình Web sẽ bắt đầu lúc 8h ngày mai."),
                        new NotificationItem("Bài kiểm tra Toán sẽ đóng vào ngày 20.")
                ),
                true // cho phép gửi đơn
        );
    }
}
