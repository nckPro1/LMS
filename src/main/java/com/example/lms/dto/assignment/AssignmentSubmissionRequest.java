package com.example.lms.dto.assignment;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AssignmentSubmissionRequest {
    private Long assignmentId;
    private Long studentId;
    private String comment;
    private String fileUrl;
}