package com.example.lms.service;

import com.example.lms.dto.auth.AuthResponse;
import com.example.lms.dto.auth.LoginRequest;
import com.example.lms.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    /**
     * Xử lý logic đăng nhập.
     *
     * @param request DTO chứa email và mật khẩu.
     * @return DTO chứa token và thông tin người dùng.
     */
    public AuthResponse login(LoginRequest request) {
        // Xác thực người dùng bằng Spring Security
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        // Nếu xác thực thành công, tìm user trong DB
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(); // Không cần lo lắng vì authenticationManager đã kiểm tra

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