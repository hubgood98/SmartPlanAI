package com.project.smartplanai.repository;

import com.project.smartplanai.entity.ScheduleHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleHistoryRepository extends JpaRepository<ScheduleHistory, Long> {
    List<ScheduleHistory> findByScheduleId(Long scheduleId);
}
