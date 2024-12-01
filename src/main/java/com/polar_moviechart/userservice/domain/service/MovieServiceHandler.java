package com.polar_moviechart.userservice.domain.service;

import com.polar_moviechart.userservice.exception.ErrorCode;
import com.polar_moviechart.userservice.exception.UserBusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieServiceHandler {
    private final MovieServiceClient movieServiceClient;

    public void validateMovieExists(Integer movieCode) {
        boolean isMovieExists = (boolean) movieServiceClient.getRequest(movieCode);
        if (!isMovieExists) {
            throw new UserBusinessException(ErrorCode.MOVIE_NOT_EXISTS);
        }
    }
}
