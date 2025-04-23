package com.project.smartplanai.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// OpenSearch 인덱싱용 DTO
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScheduleDocument {

    private Long id;
    private String title;
    private String description;
    private String startTime;
    private String endTime;
    private String priority;
    private String status;
    private String assignedUserId;
}
