// StudentDashboardResponse.java
package com.example.lms.dto.dashboard;

import java.util.List;

public record StudentDashboardResponse(
        int progress,
        List<AssignmentItem> upcomingAssignments,
        List<NotificationItem> notifications,
        boolean allowSendApplication
) {}
