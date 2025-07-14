package com.example.lms.service.teacher.impl;

import com.example.lms.dto.assignment.AssignmentCreateRequest;
import com.example.lms.dto.assignment.AssignmentCreateResponse;
import com.example.lms.entity.Assignment;
import com.example.lms.repository.AssignmentRepository;
import com.example.lms.service.teacher.AssignmentTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;

@Service
public class AssignmentTeacherServiceImpl implements AssignmentTeacherService {

    @Autowired
    private AssignmentRepository assignmentRepository;

    private final String uploadDir = "uploads/assignments/";

    @Override
    public AssignmentCreateResponse createAssignment(AssignmentCreateRequest request) {
        String fileName = null;

        if (request.getFile() != null && !request.getFile().isEmpty()) {
            fileName = saveFile(request.getFile());
        }

        Assignment assignment = new Assignment();
        assignment.setTitle(request.getTitle());
        assignment.setDescription(request.getDescription());
        assignment.setDeadline(request.getDeadline());
        assignment.setFileUrl(fileName);
        assignment.setCreatedAt(LocalDateTime.now());

        assignment = assignmentRepository.save(assignment);

        AssignmentCreateResponse response = new AssignmentCreateResponse();
        response.setId(assignment.getId());
        response.setTitle(assignment.getTitle());
        response.setFileUrl(fileName);

        return response;
    }

    private String saveFile(MultipartFile file) {
        try {
            Files.createDirectories(Paths.get(uploadDir));
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            Path filePath = Paths.get(uploadDir, fileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            return filePath.toString();
        } catch (IOException e) {
            throw new RuntimeException("Could not store file.", e);
        }
    }
}
