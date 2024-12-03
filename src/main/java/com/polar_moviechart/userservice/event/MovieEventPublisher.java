package com.polar_moviechart.userservice.event;

import com.polar_moviechart.userservice.event.dto.UserActivityType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieEventPublisher {
    private final MovieLikeEventPublisher movieLikeEventPublisher;

    public void publishLikeEvent(Long userId, int movieCode, boolean isLike) {
        movieLikeEventPublisher.publishLikeEvent(userId, movieCode, isLike, UserActivityType.LIKE);
    }
}
