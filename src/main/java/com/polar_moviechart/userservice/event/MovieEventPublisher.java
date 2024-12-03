package com.polar_moviechart.userservice.event;

import com.polar_moviechart.userservice.config.RabbitMQProperties;
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
    private final ObjectStringConverter converter;

    public void publishLikeEvent(Long userId, int movieCode, boolean isLike) {
        String exchangeName = rabbitMQProperties.getExchange().get("main");
        String routingKey = rabbitMQProperties.getRoutingKeys().get("movie-like");

        MovieLikeMessageDto eventDto = MovieLikeMessageDto.from(userId, movieCode, isLike);
        String message = converter.convertObjectToJsonString(eventDto);
        rabbitTemplate.convertAndSend(exchangeName, routingKey, message);
        log.info("Like event published: {}", eventDto);
    }

    public void publishRatingEvent(Long userId, int code, double ratingValue) {
        String exchangeName = rabbitMQProperties.getExchange().get("main");
        String routingKey = rabbitMQProperties.getRoutingKeys().get("movie-rating");

        MovieRatingMessageDto eventDto = MovieRatingMessageDto.from(userId, code, ratingValue);
        String message = converter.convertObjectToJsonString(eventDto);
        rabbitTemplate.convertAndSend(exchangeName, routingKey, message);
        log.info("Rating event published: {}", eventDto);
    }
}
