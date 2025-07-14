package com.example.lms.service.teacher;

import com.example.lms.dto.classroom.ClassDetailDTO;
import com.example.lms.dto.schedule.AttendanceDTO;
import com.example.lms.dto.shared.SimpleUserDTO;
import com.example.lms.entity.*;
import com.example.lms.repository.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClassTeacherService {

    private final ClassroomRepository classroomRepository;
    private final ClassMemberRepository classMemberRepository;
    private final AttendanceRepository attendanceRepository;

    // TODO: giả lập lấy lớp theo teacher hiện tại (chưa dùng auth)
    public List<ClassDetailDTO> getMyClasses() {
        List<Classroom> classrooms = classroomRepository.findAll(); // thay bằng findByTeacherId nếu có
        return classrooms.stream().map(this::toDetail).collect(Collectors.toList());
    }

    public ClassDetailDTO getClassDetail(Long classId) {
        Classroom c = classroomRepository.findById(classId)
                .orElseThrow(() -> new EntityNotFoundException("Class not found"));
        return toDetail(c);
    }

    public void submitAttendance(Long scheduleId, List<AttendanceDTO> attendanceList) {
        List<Attendance> records = attendanceList.stream()
                .map(dto -> new Attendance(scheduleId, dto.getUserId(), dto.getStatus()))
                .collect(Collectors.toList());
        attendanceRepository.saveAll(records);
    }

    private ClassDetailDTO toDetail(Classroom c) {
        List<SimpleUserDTO> members = classMemberRepository.findByClassroomId(c.getId()).stream()
                .map(cm -> new SimpleUserDTO(cm.getUser()))
                .collect(Collectors.toList());
        return new ClassDetailDTO(c.getId(), c.getName(), c.getDescription(), members);
    }
}
