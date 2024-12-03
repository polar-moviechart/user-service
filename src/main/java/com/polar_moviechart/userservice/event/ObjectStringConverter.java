package com.polar_moviechart.userservice.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ObjectStringConverter {
    private final ObjectMapper objectMapper;

    public String convertObjectToJsonString(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            log.error("객체 직렬화 중 오류 발생: {}", e.getMessage());
            return null;
        }
    }
}

