package com.project.smartplanai.controller;

import com.project.smartplanai.entity.Schedule;
import com.project.smartplanai.service.ScheduleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/schedules")
public class ScheduleController {
    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    // 일정 생성 API
    @PostMapping
    public ResponseEntity<Schedule> createSchedule(@RequestBody Schedule schedule) {
        return ResponseEntity.ok(scheduleService.create(schedule));
    }

    // 모든 일정 조회 API
    @GetMapping
    public ResponseEntity<List<Schedule>> getAllSchedules() {
        return ResponseEntity.ok(scheduleService.getAllSchedules());
    }

    // 특정 일정 조회 API
    @GetMapping("/{id}")
    public ResponseEntity<Schedule> getScheduleById(@PathVariable Long id) {
        Optional<Schedule> schedule = scheduleService.getScheduleById(id);
        return schedule.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 일정 수정 API
    @PutMapping("/{id}")
    public ResponseEntity<Schedule> updateSchedule(@PathVariable Long id, @RequestBody Schedule updatedSchedule) {
        Optional<Schedule> existingSchedule = scheduleService.getScheduleById(id);

        if (existingSchedule.isPresent()) {
            Schedule schedule = existingSchedule.get();
            scheduleService.updateSchedule(updatedSchedule);
            return ResponseEntity.ok(updatedSchedule);
        }
        return ResponseEntity.notFound().build();
    }

    // 일정 삭제 API
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSchedule(@PathVariable Long id) {
        scheduleService.deleteSchedule(id);
        return ResponseEntity.ok("일정 삭제 완료");
    }
}
