package com.example.lms.dto.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateProfileRequest {
    // Hiện tại chỉ cho phép cập nhật họ tên, có thể mở rộng sau
    @NotBlank(message = "Họ tên không được để trống")
    private String fullName;
}