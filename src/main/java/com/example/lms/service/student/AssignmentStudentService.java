package com.example.lms.service.student;

import com.example.lms.dto.assignment.AssignmentSubmissionRequest;
import com.example.lms.dto.assignment.AssignmentSubmissionResponse;

import java.util.List;

public interface AssignmentStudentService {
    AssignmentSubmissionResponse submitAssignment(Long assignmentId, Long studentId, String comment, MultipartFile file);
    List<AssignmentSubmissionResponse> getStudentSubmissions(Long studentId);
}