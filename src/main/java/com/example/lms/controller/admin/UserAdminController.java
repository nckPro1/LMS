package com.example.lms.controller.admin;

import com.example.lms.dto.auth.AuthResponse;
import com.example.lms.dto.auth.RegisterRequest;
import com.example.lms.service.admin.AdminUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin/users") // Đường dẫn dành riêng cho quản lý người dùng
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')") // Yêu cầu quyền ADMIN cho tất cả các API trong controller này
public class UserAdminController {

    private final AdminUserService adminUserService;

    /**
     * Endpoint cho Admin tạo tài khoản mới
     * Phục vụ UC-05: Create User Account
     */
    @PostMapping
    public ResponseEntity<AuthResponse> register(
            @Valid @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(adminUserService.register(request));
    }

    // Các API khác của Admin liên quan đến User sẽ được thêm vào đây
    // Ví dụ: GET /, GET /{id}, PUT /{id}/status
}