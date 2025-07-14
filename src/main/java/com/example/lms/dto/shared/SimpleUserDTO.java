package com.example.lms.dto.shared;

import com.example.lms.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SimpleUserDTO {
    private Long id;
    private String name;
    private String email;

    public SimpleUserDTO(User user) {
        this.id = user.getId();
        this.name = user.getFullName();
        this.email = user.getEmail();
    }
}
