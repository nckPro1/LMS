// File: src/main/java/com/example/lms/entity/User.java
package com.example.lms.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
// Sử dụng backticks (`) vì 'user' là một từ khóa trong SQL
@Table(name = "`user`")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // Đổi từ Long sang Integer để khớp với INT trong DB

    @Column(name = "full_name", nullable = false, length = 100)
    private String fullName;

    @Column(name = "identifier_code", unique = true, nullable = false, length = 20)
    private String identifierCode; // Thêm trường mã định danh

    @Column(name = "email", unique = true, nullable = false, length = 100)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    @Column(name = "is_active")
    private boolean isActive = true; // Thêm trường trạng thái active

    @Column(name = "reset_token")
    private String resetToken; // Thêm trường reset token

    @Column(name = "reset_token_expiry")
    private LocalDateTime resetTokenExpiry; // Thêm trường thời gian hết hạn token

    // --- Triển khai các phương thức của UserDetails ---

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Trả về một danh sách quyền, ở đây là role của user
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        // Spring Security sẽ sử dụng email để xác thực
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        // Trạng thái của tài khoản sẽ được quyết định bởi trường isActive
        return this.isActive;
    }
}