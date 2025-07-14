package com.example.lms.dto.classroom;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClassResponse {
    private Long id;
    private String name;
    private String description;
}
