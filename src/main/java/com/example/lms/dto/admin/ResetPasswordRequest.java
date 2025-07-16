package com.example.lms.dto.admin;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * DTO để Admin yêu cầu reset mật khẩu cho người dùng.
 * Phục vụ UC-08: Reset User Password.
 */
@Data
public class ResetPasswordRequest {
    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email không đúng định dạng")
    private String email;
}