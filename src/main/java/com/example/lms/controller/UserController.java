package com.example.lms.controller;

import com.example.lms.dto.user.ChangePasswordRequest;
import com.example.lms.dto.user.UpdateProfileRequest;
import com.example.lms.entity.User;
import com.example.lms.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * Endpoint cho người dùng tự đổi mật khẩu của mình.
     * Phục vụ UC-02: Change Password
     */
    @PatchMapping("/password")
    public ResponseEntity<Void> changePassword(
            @Valid @RequestBody ChangePasswordRequest request,
            Principal connectedUser
    ) {
        userService.changePassword(request, connectedUser);
        return ResponseEntity.ok().build();
    }

    /**
     * Endpoint cho người dùng tự cập nhật thông tin cá nhân.
     * Phục vụ UC-04: Update Personal Info
     */
    @PutMapping("/profile")
    public ResponseEntity<User> updateProfile(
            @Valid @RequestBody UpdateProfileRequest request,
            Principal connectedUser
    ) {
        User updatedUser = userService.updateProfile(request, connectedUser);
        return ResponseEntity.ok(updatedUser);
    }
}