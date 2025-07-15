package com.example.lms.service.teacher;

import com.example.lms.dto.assignment.AssignmentCreateRequest;
import com.example.lms.dto.assignment.AssignmentCreateResponse;

public interface AssignmentTeacherService {
    AssignmentCreateResponse createAssignment(AssignmentCreateRequest request);
}
