package com.example.lms.service.student.student.impl;

import com.example.lms.dto.assignment.AssignmentSubmissionRequest;
import com.example.lms.dto.assignment.AssignmentSubmissionResponse;
import com.example.lms.entity.Assignment;
import com.example.lms.entity.AssignmentSubmission;
import com.example.lms.entity.User;
import com.example.lms.repository.AssignmentSubmissionRepository;
import com.example.lms.repository.UserRepository;
import com.example.lms.repository.AssignmentRepository;
import com.example.lms.service.student.AssignmentStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssignmentStudentServiceImpl implements AssignmentStudentService {

    @Autowired
    private AssignmentSubmissionRepository submissionRepository;

    @Autowired
    private AssignmentRepository assignmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public AssignmentSubmissionResponse submitAssignment(AssignmentSubmissionRequest request) {
        AssignmentSubmission submission = new AssignmentSubmission();
        submission.setAssignment(assignmentRepository.findById(request.getAssignmentId()).orElse(null));
        submission.setStudent(userRepository.findById(request.getStudentId()).orElse(null));
        submission.setComment(request.getComment());
        submission.setFileUrl(request.getFileUrl());
        submission.setSubmittedAt(LocalDateTime.now());

        submission = submissionRepository.save(submission);

        AssignmentSubmissionResponse response = new AssignmentSubmissionResponse();
        response.setId(submission.getId());
        response.setAssignmentId(submission.getAssignment().getId());
        response.setStudentId(submission.getStudent().getId());
        response.setComment(submission.getComment());
        response.setFileUrl(submission.getFileUrl());
        response.setSubmittedAt(submission.getSubmittedAt());
        response.setScore(submission.getScore());

        return response;
    }

    @Override
    public List<AssignmentSubmissionResponse> getStudentSubmissions(Long studentId) {
        return submissionRepository.findByStudentId(studentId).stream().map(submission -> {
            AssignmentSubmissionResponse res = new AssignmentSubmissionResponse();
            res.setId(submission.getId());
            res.setAssignmentId(submission.getAssignment().getId());
            res.setStudentId(submission.getStudent().getId());
            res.setComment(submission.getComment());
            res.setFileUrl(submission.getFileUrl());
            res.setSubmittedAt(submission.getSubmittedAt());
            res.setScore(submission.getScore());
            return res;
        }).collect(Collectors.toList());
    }

    @Service
    public class FileStorageServiceImpl implements FileStorageService {

        private final String uploadDir = "uploads";

        @Override
        public String store(MultipartFile file) {
            try {
                Files.createDirectories(Paths.get(uploadDir));
                String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();
                Path filePath = Paths.get(uploadDir).resolve(filename);
                Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
                return "/files/" + filename; // URL giả định
            } catch (IOException e) {
                throw new RuntimeException("Failed to store file", e);
            }
        }
    }
}
