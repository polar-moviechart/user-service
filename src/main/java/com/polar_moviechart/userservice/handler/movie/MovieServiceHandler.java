package com.polar_moviechart.userservice.handler.movie;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.polar_moviechart.userservice.controller.secureapi.dtos.MovieDto;
import com.polar_moviechart.userservice.exception.ErrorCode;
import com.polar_moviechart.userservice.exception.UserBusinessException;
import com.polar_moviechart.userservice.utils.CustomResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class MovieServiceHandler {
    private final MovieServiceClient movieServiceClient;

    private final ObjectMapper objectMapper;

    public void validateMovieExists(Integer movieCode) {
        boolean isMovieExists = (boolean) movieServiceClient.getRequest(movieCode);
        if (!isMovieExists) {
            throw new UserBusinessException(ErrorCode.MOVIE_NOT_EXISTS);
        }
    }

    public List<MovieDto> getMoviesByCodes(List<Integer> codes) {
        String endPoint = "/codes";
        List<MovieDto> movieDtos = movieServiceClient.sendPostRequest(
                endPoint, null, codes, new ParameterizedTypeReference<CustomResponse<List<MovieDto>>>() {}
        );
        return movieDtos;
    }
}
