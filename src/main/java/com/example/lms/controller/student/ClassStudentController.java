package com.example.lms.controller.student;

import com.example.lms.dto.classroom.ClassDetailDTO;
import com.example.lms.service.student.ClassStudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student/classes")
@RequiredArgsConstructor
public class ClassStudentController {

    private final ClassStudentService classStudentService;

    // Xem danh sách lớp mà học sinh đang học
    @GetMapping
    public ResponseEntity<List<ClassDetailDTO>> getMyClasses() {
        return ResponseEntity.ok(classStudentService.getMyClasses());
    }

    // Xem chi tiết lớp (bao gồm thành viên khác)
    @GetMapping("/{classId}")
    public ResponseEntity<ClassDetailDTO> getClassDetail(@PathVariable Long classId) {
        return ResponseEntity.ok(classStudentService.getClassDetail(classId));
    }

    // Vào lớp học online
    @GetMapping("/{classId}/join")
    public ResponseEntity<String> joinOnlineClass(@PathVariable Long classId) {
        return ResponseEntity.ok("https://example.com/classroom/" + classId);
    }
}
