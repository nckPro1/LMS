package com.example.lms.dto.lesson;

import lombok.Data;

@Data
public class LessonLogRequest {
    private Long studentId;
    private Long lessonId;
}