package com.example.lms.controller.student;

import com.example.lms.dto.lesson.LessonLogRequest;
import com.example.lms.dto.lesson.LessonVideoResponse;
import com.example.lms.service.student.LessonVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student/lessons")
public class LessonVideoController {

    @Autowired
    private LessonVideoService lessonVideoService;

    @GetMapping("/{lessonId}")
    public LessonVideoResponse getLessonVideo(@PathVariable Long lessonId) {
        return lessonVideoService.viewLesson(lessonId);
    }

    @PostMapping("/log")
    public void logLessonView(@RequestBody LessonLogRequest request) {
        lessonVideoService.logLessonView(request.getStudentId(), request.getLessonId());
    }
}