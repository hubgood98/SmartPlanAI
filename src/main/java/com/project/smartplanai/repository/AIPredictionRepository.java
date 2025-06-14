package com.project.smartplanai.repository;

import com.project.smartplanai.entity.AIPrediction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AIPredictionRepository extends JpaRepository<AIPrediction, Long> {
    Optional<AIPrediction> findByScheduleId(Long scheduleId);
}
