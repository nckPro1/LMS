package com.example.lms.service.student;

import com.example.lms.dto.lesson.LessonVideoResponse;

public interface LessonVideoService {
    LessonVideoResponse viewLesson(Long lessonId);
    void logLessonView(Long studentId, Long lessonId);
}