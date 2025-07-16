// AdminDashboardResponse.java
package com.example.lms.dto.dashboard;

import java.util.List;

public record AdminDashboardResponse(
        int totalStudents,
        int totalTeachers,
        int totalAdmins,
        int totalCourses,
        int totalClasses,
        List<UserStatusItem> recentUserChanges,
        List<ActivityLogItem> recentActivities
) {}
