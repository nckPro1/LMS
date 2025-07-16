package com.example.lms.service.admin;

import com.example.lms.dto.auth.AuthResponse;
import com.example.lms.dto.auth.RegisterRequest;
import com.example.lms.entity.User;
import com.example.lms.exception.DuplicateResourceException;
import com.example.lms.repository.UserRepository;
import com.example.lms.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    /**
     * Xử lý logic đăng ký người dùng mới (chỉ dành cho Admin).
     *
     * @param request DTO chứa thông tin đăng ký.
     * @return DTO chứa token và thông tin người dùng.
     */
    public AuthResponse register(RegisterRequest request) {
        // Kiểm tra email hoặc mã định danh đã tồn tại chưa
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException("Email đã được sử dụng: " + request.getEmail());
        }
        if (userRepository.existsByIdentifierCode(request.getIdentifierCode())) {
            throw new DuplicateResourceException("Mã định danh đã được sử dụng: " + request.getIdentifierCode());
        }

        // Tạo đối tượng User mới
        var user = User.builder()
                .fullName(request.getFullName())
                .identifierCode(request.getIdentifierCode())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword())) // Mã hóa mật khẩu
                .role(request.getRole())
                .isActive(true) // Mặc định là active
                .build();

        // Lưu user vào database
        userRepository.save(user);

        // Tạo JWT token
        var jwtToken = jwtService.generateToken(user);

        // Trả về response
        return AuthResponse.builder()
                .token(jwtToken)
                .email(user.getEmail())
                .fullName(user.getFullName())
                .role(user.getRole())
                .identifierCode(user.getIdentifierCode())
                .build();
    }
}