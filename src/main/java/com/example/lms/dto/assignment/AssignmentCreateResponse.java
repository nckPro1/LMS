package com.example.lms.dto.assignment;

import lombok.Data;

@Data
public class AssignmentCreateResponse {
    private Long id;
    private String title;
    private String fileUrl;
}
