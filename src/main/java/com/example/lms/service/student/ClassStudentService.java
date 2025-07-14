package com.example.lms.service.student;

import com.example.lms.dto.classroom.ClassDetailDTO;
import com.example.lms.dto.shared.SimpleUserDTO;
import com.example.lms.entity.Classroom;
import com.example.lms.repository.ClassMemberRepository;
import com.example.lms.repository.ClassroomRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClassStudentService {

    private final ClassroomRepository classroomRepository;
    private final ClassMemberRepository classMemberRepository;

    public List<ClassDetailDTO> getMyClasses() {
        List<Classroom> classrooms = classroomRepository.findAll(); // TODO: Lọc theo studentId
        return classrooms.stream().map(this::toDetail).collect(Collectors.toList());
    }

    public ClassDetailDTO getClassDetail(Long classId) {
        Classroom c = classroomRepository.findById(classId)
                .orElseThrow(() -> new EntityNotFoundException("Class not found"));
        return toDetail(c);
    }

    private ClassDetailDTO toDetail(Classroom c) {
        List<SimpleUserDTO> members = classMemberRepository.findByClassroomId(c.getId()).stream()
                .map(cm -> new SimpleUserDTO(cm.getUser()))
                .collect(Collectors.toList());
        return new ClassDetailDTO(c.getId(), c.getName(), c.getDescription(), members);
    }
}
