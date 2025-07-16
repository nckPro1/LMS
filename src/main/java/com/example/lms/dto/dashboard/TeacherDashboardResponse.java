// TeacherDashboardResponse.java
package com.example.lms.dto.dashboard;

import java.util.List;

public record TeacherDashboardResponse(
        int totalClasses,
        int totalAssignments,
        List<ClassProgressItem> classProgress,
        List<StudentAlertItem> lowPerformingStudents,
        List<AssignmentItem> pendingAssignments
) {}
