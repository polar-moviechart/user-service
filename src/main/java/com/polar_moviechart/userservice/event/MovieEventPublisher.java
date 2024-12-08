package com.polar_moviechart.userservice.event;

import com.polar_moviechart.userservice.config.RabbitMQProperties;
import com.polar_moviechart.userservice.domain.service.movie.dtos.UpdateRatingRes;
import com.polar_moviechart.userservice.event.dto.MovieLikeMessageDto;
import com.polar_moviechart.userservice.event.dto.MovieRatingMessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieEventPublisher {
    private final RabbitMQProperties rabbitMQProperties;
    private final RabbitTemplate rabbitTemplate;

    public void publishLikeEvent(Long userId, int movieCode, boolean isLike) {
        String exchangeName = rabbitMQProperties.getExchange().get("main");
        String routingKey = rabbitMQProperties.getRoutingKeys().get("movie-like");

        MovieLikeMessageDto eventDto = MovieLikeMessageDto.from(userId, movieCode, isLike);
        rabbitTemplate.convertAndSend(exchangeName, routingKey, eventDto);
        log.info("Like event published: {}", eventDto);
    }

    public void publishRatingEvent(Long userId, int code, UpdateRatingRes updateRatingRes) {
        String exchangeName = rabbitMQProperties.getExchange().get("main");
        String routingKey = rabbitMQProperties.getRoutingKeys().get("movie-rating");

        MovieRatingMessageDto eventDto = MovieRatingMessageDto.from(userId, code, updateRatingRes);
        rabbitTemplate.convertAndSend(exchangeName, routingKey, eventDto);
        log.info("Rating event published: {}", eventDto);
    }
}
