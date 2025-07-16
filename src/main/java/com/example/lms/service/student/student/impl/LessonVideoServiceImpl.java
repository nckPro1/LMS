package com.example.lms.service.student.student.impl;

import com.example.lms.dto.lesson.LessonVideoResponse;
import com.example.lms.entity.Lesson;
import com.example.lms.entity.LessonLog;
import com.example.lms.entity.User;
import com.example.lms.repository.LessonLogRepository;
import com.example.lms.repository.LessonRepository;
import com.example.lms.repository.UserRepository;
import com.example.lms.service.student.LessonVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LessonVideoServiceImpl implements LessonVideoService {

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LessonLogRepository lessonLogRepository;

    @Override
    public LessonVideoResponse viewLesson(Long lessonId) {
        Lesson lesson = lessonRepository.findById(lessonId).orElseThrow();
        LessonVideoResponse response = new LessonVideoResponse();
        response.setLessonId(lesson.getId());
        response.setTitle(lesson.getTitle());
        response.setVideoUrl(lesson.getVideoUrl());
        return response;
    }

    @Override
    public void logLessonView(Long studentId, Long lessonId) {
        Lesson lesson = lessonRepository.findById(lessonId).orElseThrow();
        User student = userRepository.findById(studentId).orElseThrow();

        LessonLog log = new LessonLog();
        log.setLesson(lesson);
        log.setStudent(student);
        log.setViewedAt(LocalDateTime.now());
        lessonLogRepository.save(log);
    }
}
