package com.example.lms.dto.admin;

import com.example.lms.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * DTO để trả về thông tin chi tiết của người dùng cho Admin.
 * Phục vụ UC-06: View User Account Details.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailResponse {
    private Integer id;
    private String fullName;
    private String identifierCode;
    private String email;
    private Role role;
    private boolean isActive;
    private LocalDateTime resetTokenExpiry;
}