package com.project.smartplanai.controller;

import com.project.smartplanai.entity.Schedule;
import com.project.smartplanai.service.ScheduleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Tag(name="일정 API", description = "스마트팜 일정 CRUD 및 OpenSearch 연동 API")
@RequestMapping("/api/schedules")
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping("/create")
    @Operation(summary = "일정 생성", description = "DB(추가예정)와 OpenSearch에 동시에 일정을 저장함")
    public ResponseEntity<Schedule> createSchedule(@RequestBody Schedule schedule) {
        return ResponseEntity.ok(scheduleService.create(schedule));
    }

    @GetMapping
    @Operation(summary = "전체 일정 조회", description = "등록된 모든 일정을 반환합니다.")
    public ResponseEntity<List<Schedule>> getAll() {
        return ResponseEntity.ok(scheduleService.getAllSchedules());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Schedule>> getByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(scheduleService.getSchedulesByUser(userId));
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
