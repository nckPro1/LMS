package com.example.lms.dto.lesson;

import lombok.Data;

@Data
public class LessonVideoResponse {
    private Long lessonId;
    private String title;
    private String videoUrl;
}