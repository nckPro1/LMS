package com.example.lms.service.student;

import com.example.lms.dto.schedule.ScheduleResponse;
import com.example.lms.entity.Schedule;
import com.example.lms.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleStudentService {

    private final ScheduleRepository scheduleRepository;

    public List<ScheduleResponse> getSchedulesByClassId(Long classId) {
        return scheduleRepository.findAll().stream()
                .filter(s -> s.getClassroom().getId().equals(classId))
                .map(s -> new ScheduleResponse(
                        s.getId(),
                        s.getClassroom().getId(),
                        s.getDayOfWeek(),
                        s.getStartTime(),
                        s.getEndTime()
                )).collect(Collectors.toList());
    }
}
