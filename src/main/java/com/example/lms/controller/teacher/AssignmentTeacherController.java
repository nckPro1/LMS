package com.example.lms.controller.teacher;

import com.example.lms.dto.assignment.AssignmentCreateRequest;
import com.example.lms.dto.assignment.AssignmentCreateResponse;
import com.example.lms.service.teacher.AssignmentTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/teacher/assignments")
public class AssignmentTeacherController {

    @Autowired
    private AssignmentTeacherService assignmentTeacherService;

    @PostMapping("/create")
    public AssignmentCreateResponse createAssignment(
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam Long courseId,
            @RequestParam String deadline,
            @RequestParam(required = false) MultipartFile file
    ) {
        AssignmentCreateRequest request = new AssignmentCreateRequest();
        request.setTitle(title);
        request.setDescription(description);
        request.setCourseId(courseId);
        request.setDeadline(LocalDateTime.parse(deadline));
        request.setFile(file);
        return assignmentTeacherService.createAssignment(request);
    }
}
