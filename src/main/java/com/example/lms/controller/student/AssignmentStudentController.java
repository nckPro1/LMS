package com.example.lms.controller.student;

import com.example.lms.dto.assignment.AssignmentSubmissionRequest;
import com.example.lms.dto.assignment.AssignmentSubmissionResponse;
import com.example.lms.service.student.AssignmentStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student/assignments")
public class AssignmentStudentController {

    @Autowired
    private AssignmentStudentService assignmentStudentService;

    @PostMapping("/submit")
    public AssignmentSubmissionResponse submitAssignment(@RequestBody AssignmentSubmissionRequest request) {
        return assignmentStudentService.submitAssignment(request);
    }

    @GetMapping("/student/{studentId}")
    public List<AssignmentSubmissionResponse> getSubmissions(@PathVariable Long studentId) {
        return assignmentStudentService.getStudentSubmissions(studentId);
    }
}