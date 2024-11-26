package com.polar_moviechart.userservice.domain.service.event;

import com.polar_moviechart.userservice.domain.dto.MovieLikeMessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieLikeEventPublisher {

    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    private final RabbitTemplate rabbitTemplate;

    public void publishLikeEvent(Long userId, int movieCode, Integer likeCnt) {
        MovieLikeMessageDto eventDto = MovieLikeMessageDto.builder()
                .userId(userId)
                .movieCode(movieCode)
                .likeCnt(likeCnt)
                .build();

        rabbitTemplate.convertAndSend(exchangeName, routingKey, eventDto);
        log.info("Like event published: {}", eventDto);
    }
}