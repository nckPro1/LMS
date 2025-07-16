package com.example.lms.service;

import com.example.lms.dto.user.ChangePasswordRequest;
import com.example.lms.dto.user.UpdateProfileRequest;
import com.example.lms.entity.User;
import com.example.lms.exception.InvalidPasswordException;
import com.example.lms.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    /**
     * Thay đổi mật khẩu cho người dùng đang được xác thực.
     *
     * @param request DTO chứa mật khẩu cũ và mới.
     * @param connectedUser Đối tượng Principal chứa thông tin người dùng đang đăng nhập.
     */
    public void changePassword(ChangePasswordRequest request, Principal connectedUser) {
        // Lấy thông tin người dùng từ Principal (được Spring Security cung cấp)
        var user = (User) connectedUser;

        // Kiểm tra mật khẩu cũ có khớp không
        if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            throw new InvalidPasswordException("Mật khẩu cũ không chính xác");
        }

        // Mã hóa và cập nhật mật khẩu mới
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));

        // Lưu lại vào database
        userRepository.save(user);
    }

    /**
     * Cập nhật thông tin cá nhân cho người dùng đang được xác thực.
     *
     * @param request DTO chứa thông tin cần cập nhật.
     * @param connectedUser Đối tượng Principal chứa thông tin người dùng đang đăng nhập.
     * @return User đã được cập nhật.
     */
    public User updateProfile(UpdateProfileRequest request, Principal connectedUser) {
        var user = (User) connectedUser;

        // Cập nhật các trường thông tin
        user.setFullName(request.getFullName());

        // Lưu và trả về thông tin đã cập nhật
        return userRepository.save(user);
    }
}