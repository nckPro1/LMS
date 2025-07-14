package com.example.lms.dto.classroom;

import com.example.lms.dto.shared.SimpleUserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ClassDetailDTO {
    private Long id;
    private String name;
    private String description;
    private List<SimpleUserDTO> members;
}
