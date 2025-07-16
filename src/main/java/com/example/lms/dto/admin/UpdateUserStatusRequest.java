package com.example.lms.dto.admin;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * DTO để Admin cập nhật trạng thái hoạt động của người dùng.
 * Phục vụ UC-07: Modify User Account Status.
 */
@Data
public class UpdateUserStatusRequest {
    @NotNull(message = "Trạng thái không được để trống")
    private Boolean isActive;
}