// UserStatusItem.java
package com.example.lms.dto.dashboard;

import java.time.LocalDateTime;

public record UserStatusItem(String fullName, String role, String status, LocalDateTime timestamp) {}
