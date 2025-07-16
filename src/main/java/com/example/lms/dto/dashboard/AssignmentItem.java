// AssignmentItem.java
package com.example.lms.dto.dashboard;

import java.time.LocalDateTime;

public record AssignmentItem(String title, String description, LocalDateTime dueDate) {}
