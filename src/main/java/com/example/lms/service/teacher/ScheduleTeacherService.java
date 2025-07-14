package com.example.lms.service.teacher;

import com.example.lms.dto.schedule.ScheduleResponse;
import com.example.lms.entity.Schedule;
import com.example.lms.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleTeacherService {

    private final ScheduleRepository scheduleRepository;

    public List<ScheduleResponse> getMySchedules() {
        // TODO: Lọc theo giáo viên hiện tại (chưa dùng auth nên trả toàn bộ)
        return scheduleRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public List<ScheduleResponse> getSchedulesByClassId(Long classId) {
        return scheduleRepository.findAll().stream()
                .filter(s -> s.getClassroom().getId().equals(classId))
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    private ScheduleResponse toResponse(Schedule s) {
        return new ScheduleResponse(
                s.getId(),
                s.getClassroom().getId(),
                s.getDayOfWeek(),
                s.getStartTime(),
                s.getEndTime()
        );
    }
}
