package com.example.lms.service.admin;

import com.example.lms.dto.classroom.ClassDetailDTO;
import com.example.lms.dto.classroom.ClassRequest;
import com.example.lms.dto.classroom.ClassResponse;
import com.example.lms.dto.shared.SimpleUserDTO;
import com.example.lms.entity.ClassMember;
import com.example.lms.entity.Classroom;
import com.example.lms.entity.User;
import com.example.lms.repository.ClassMemberRepository;
import com.example.lms.repository.ClassroomRepository;
import com.example.lms.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClassAdminService {

    private final ClassroomRepository classroomRepository;
    private final ClassMemberRepository classMemberRepository;
    private final UserRepository userRepository;

    public ClassResponse createClass(ClassRequest request) {
        Classroom classroom = new Classroom();
        classroom.setName(request.getName());
        classroom.setDescription(request.getDescription());
        Classroom saved = classroomRepository.save(classroom);
        return mapToResponse(saved);
    }

    public ClassResponse updateClass(Long id, ClassRequest request) {
        Classroom classroom = classroomRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Class not found"));
        classroom.setName(request.getName());
        classroom.setDescription(request.getDescription());
        return mapToResponse(classroomRepository.save(classroom));
    }

    public void deleteClass(Long id) {
        classroomRepository.deleteById(id);
    }

    public void assignUsers(Long classId, List<Long> userIds) {
        Classroom classroom = classroomRepository.findById(classId)
                .orElseThrow(() -> new EntityNotFoundException("Class not found"));
        List<User> users = userRepository.findAllById(userIds);
        List<ClassMember> members = users.stream()
                .map(user -> new ClassMember(classroom, user))
                .collect(Collectors.toList());
        classMemberRepository.saveAll(members);
    }

    public List<ClassResponse> getAll() {
        return classroomRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public ClassDetailDTO getDetail(Long id) {
        Classroom classroom = classroomRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Class not found"));
        List<SimpleUserDTO> members = classMemberRepository.findByClassroomId(id).stream()
                .map(cm -> new SimpleUserDTO(cm.getUser()))
                .collect(Collectors.toList());
        return new ClassDetailDTO(classroom.getId(), classroom.getName(), classroom.getDescription(), members);
    }

    private ClassResponse mapToResponse(Classroom classroom) {
        return new ClassResponse(classroom.getId(), classroom.getName(), classroom.getDescription());
    }
}
