package com.example.lms.service.admin;

import com.example.lms.dto.schedule.ScheduleRequest;
import com.example.lms.dto.schedule.ScheduleResponse;
import com.example.lms.entity.Classroom;
import com.example.lms.entity.Schedule;
import com.example.lms.repository.ClassroomRepository;
import com.example.lms.repository.ScheduleRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleAdminService {

    private final ScheduleRepository scheduleRepository;
    private final ClassroomRepository classroomRepository;

    public ScheduleResponse create(ScheduleRequest request) {
        Classroom classroom = classroomRepository.findById(request.getClassroomId())
                .orElseThrow(() -> new EntityNotFoundException("Classroom not found"));
        Schedule schedule = new Schedule(classroom, request.getDayOfWeek(), request.getStartTime(), request.getEndTime());
        return toResponse(scheduleRepository.save(schedule));
    }

    public ScheduleResponse update(Long id, ScheduleRequest request) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Schedule not found"));
        Classroom classroom = classroomRepository.findById(request.getClassroomId())
                .orElseThrow(() -> new EntityNotFoundException("Classroom not found"));
        schedule.setClassroom(classroom);
        schedule.setDayOfWeek(request.getDayOfWeek());
        schedule.setStartTime(request.getStartTime());
        schedule.setEndTime(request.getEndTime());
        return toResponse(scheduleRepository.save(schedule));
    }

    public void delete(Long id) {
        scheduleRepository.deleteById(id);
    }

    public List<ScheduleResponse> getAll() {
        return scheduleRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public ScheduleResponse getById(Long id) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Schedule not found"));
        return toResponse(schedule);
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
