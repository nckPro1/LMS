package com.example.lms.repository;

import com.example.lms.entity.LessonLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonLogRepository extends JpaRepository<LessonLog, Long> {
}