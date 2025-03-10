package com.project.smartplanai.repository;

import com.project.smartplanai.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByAssignedUserId(Long userId);
    List<Schedule> findByStatus(String status);
}
