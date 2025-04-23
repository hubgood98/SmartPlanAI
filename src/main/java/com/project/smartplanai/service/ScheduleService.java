package com.project.smartplanai.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.smartplanai.dto.ScheduleDocument;
import com.project.smartplanai.entity.Schedule;
import com.project.smartplanai.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.opensearch.action.index.IndexRequest;
import org.opensearch.client.RequestOptions;
import org.opensearch.client.RestHighLevelClient;
import org.opensearch.common.xcontent.XContentType;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final RestHighLevelClient openSearchClient;
    private final ObjectMapper objectMapper;


    public Schedule create(Schedule schedule) {

        Schedule saved = scheduleRepository.save(schedule);

        ScheduleDocument doc = ScheduleDocument.builder()
                .id(saved.getId())
                .title(saved.getTitle())
                .description(saved.getDescription())
                .startTime(saved.getStartTime().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                .endTime(saved.getEndTime().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                .priority(saved.getPriority().name())
                .status(saved.getStatus().name())
                .assignedUserId(
                        saved.getAssignedUser() != null ? saved.getAssignedUser().getId().toString() : null
                )
                .build();

        try{
            String json = objectMapper.writeValueAsString(doc); //java객체 doc를 json 문자열로 변환하겠다

            Long id = saved.getId();
            IndexRequest request = new IndexRequest("schedules") //OpenSearch의 "schedules"라는 인덱스에 문서 저장 요청 생성
                    .id(id.toString()) //IndexRequest는 무조건 String타입이 들어가야 함 (공식문서에 IndexRequest id(String id)이렇게 설계되있음)
                    .source(json, XContentType.JSON);

            openSearchClient.index(request, RequestOptions.DEFAULT); //OpenSearch에 문서를 저장하는 핵심 명령어+

        }catch(Exception e){
            e.printStackTrace();
        }
        return saved;
    }

    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    public Optional<Schedule> getScheduleById(Long id){
        return scheduleRepository.findById(id);
    }

    public Schedule updateSchedule(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    public void deleteSchedule(Long id) {
        scheduleRepository.deleteById(id);
    }
}
