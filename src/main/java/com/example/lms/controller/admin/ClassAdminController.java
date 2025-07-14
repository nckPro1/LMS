package com.example.lms.controller.admin;

import com.example.lms.dto.classroom.ClassDetailDTO;
import com.example.lms.dto.classroom.ClassRequest;
import com.example.lms.dto.classroom.ClassResponse;
import com.example.lms.service.admin.ClassAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/classes")
@RequiredArgsConstructor
public class ClassAdminController {

    private final ClassAdminService classAdminService;

    /**
     * Tạo lớp học mới
     */
    @PostMapping
    public ResponseEntity<ClassResponse> createClass(@RequestBody ClassRequest request) {
        ClassResponse created = classAdminService.createClass(request);
        return ResponseEntity.ok(created);
    }

    /**
     * Cập nhật lớp học
     */
    @PutMapping("/{id}")
    public ResponseEntity<ClassResponse> updateClass(@PathVariable Long id,
                                                     @RequestBody ClassRequest request) {
        ClassResponse updated = classAdminService.updateClass(id, request);
        return ResponseEntity.ok(updated);
    }

    /**
     * Xóa lớp học
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClass(@PathVariable Long id) {
        classAdminService.deleteClass(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Gán user vào lớp học
     */
    @PostMapping("/{id}/assign")
    public ResponseEntity<Void> assignUsers(@PathVariable Long id, @RequestBody List<Long> userIds) {
        classAdminService.assignUsers(id, userIds);
        return ResponseEntity.ok().build();
    }

    /**
     * Lấy danh sách tất cả lớp học
     */
    @GetMapping
    public ResponseEntity<List<ClassResponse>> getAllClasses() {
        List<ClassResponse> classes = classAdminService.getAll();
        return ResponseEntity.ok(classes);
    }

    /**
     * Lấy chi tiết lớp học + thành viên
     */
    @GetMapping("/{id}")
    public ResponseEntity<ClassDetailDTO> getClassDetail(@PathVariable Long id) {
        ClassDetailDTO detail = classAdminService.getDetail(id);
        return ResponseEntity.ok(detail);
    }
}
