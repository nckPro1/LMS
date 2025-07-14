package com.example.lms.dto.assignment;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AssignmentSubmissionResponse {
    private Long id;
    private Long assignmentId;
    private Long studentId;
    private String comment;
    private String fileUrl;
    private LocalDateTime submittedAt;
    private Double score;
}
