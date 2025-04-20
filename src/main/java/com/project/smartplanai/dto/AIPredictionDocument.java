package com.project.smartplanai.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AIPredictionDocument {
    private Long id;                    // AIPrediction ID
    private String scheduleTitle;       // 일정 제목
    private String scheduleDescription; // 일정 설명
    private String assignedUsername;    // 담당자 이름
    private String priority;            // 일정 우선순위 (예: HIGH)
    private String status;              // 일정 상태 (예: IN_PROGRESS)
    private LocalDateTime predictedEndTime; // AI 예측 종료 시간
    private Double delayProbability;    // 지연 확률
    private LocalDateTime createdAt;    // 예측 생성 시각
}
