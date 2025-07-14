package com.example.lms.dto.assignment;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
public class AssignmentCreateRequest {
    private String title;
    private String description;
    private Long courseId;
    private LocalDateTime deadline;
    private MultipartFile file; // file đính kèm
}
