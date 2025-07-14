package com.example.lms.service.student;

import com.example.lms.dto.assignment.AssignmentSubmissionRequest;
import com.example.lms.dto.assignment.AssignmentSubmissionResponse;

import java.util.List;

public interface AssignmentStudentService {
    AssignmentSubmissionResponse submitAssignment(AssignmentSubmissionRequest request);
    List<AssignmentSubmissionResponse> getStudentSubmissions(Long studentId);
}