package com.polar_moviechart.userservice.handler.movie;

import com.polar_moviechart.userservice.utils.CustomResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieServiceClient {
    private final RestTemplate restTemplate;

    @Value("${movie-service.url}")
    private String movieServiceUrl;

    public Object getRequest(Integer movieCode) {
        String requestUrl = movieServiceUrl + movieCode;

        return restTemplate.getForEntity(requestUrl, CustomResponse.class)
                .getBody().getData();
    }

    public Object postRequest(Integer movieCode, Object requestBody) {
        String requestUrl = movieServiceUrl + movieCode;

        return restTemplate.postForEntity(requestUrl, requestBody, CustomResponse.class)
                .getBody().getData();
    }
}
