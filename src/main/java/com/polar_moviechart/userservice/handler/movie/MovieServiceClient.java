package com.polar_moviechart.userservice.handler.movie;

import com.polar_moviechart.userservice.utils.CustomResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class MovieServiceClient {
    private final RestTemplate restTemplate;

    @Value("${movie-service.url}")
    private String movieServiceUrl;

    public <T> T sendGetRequest(String endPoint, Map<String, Object> params, Class<T> responseType) {
        String requestUrl = movieServiceUrl + endPoint;
        String urlWithParams = getUrlWithParams(params, requestUrl);

        CustomResponse<T> response = restTemplate.exchange(
                urlWithParams,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<CustomResponse<T>>() {}
        ).getBody();

        if (!response.getIsSuccess()) {
            throw new IllegalArgumentException(response.getErrorMsg());
        }

        return response.getData();
    }

    public <T> T sendPostRequest(String endPoint, Map<String, Object> params, Object body, Class<T> responseType) {
        String requestUrl = movieServiceUrl + endPoint;
        String urlWithParams = getUrlWithParams(params, requestUrl);

        HttpEntity<Object> entity = new HttpEntity<>(body);

        CustomResponse<T> response = restTemplate.exchange(
                urlWithParams,
                HttpMethod.POST,
                entity,
                new ParameterizedTypeReference<CustomResponse<T>>() {}
        ).getBody();

        if (!response.getIsSuccess()) {
            throw new IllegalArgumentException(response.getErrorMsg());
        }

        return response.getData();
    }

    public <T> T sendPostRequest(String endPoint, Map<String, Object> params, Object body, ParameterizedTypeReference<CustomResponse<T>> responseType) {
        String requestUrl = movieServiceUrl + endPoint;
        String urlWithParams = getUrlWithParams(params, requestUrl);

        HttpEntity<Object> entity = new HttpEntity<>(body);
        ResponseEntity<CustomResponse<T>> responseEntity = restTemplate.exchange(
                urlWithParams,
                HttpMethod.POST,
                entity,
                responseType
        );

        CustomResponse<T> response = responseEntity.getBody();

        if (!response.getIsSuccess()) {
            throw new IllegalArgumentException(response.getErrorMsg());
        }

        return response.getData();
    }

    public Object getRequest(Integer movieCode) {
        String requestUrl = movieServiceUrl + movieCode;

        return restTemplate.getForEntity(requestUrl, CustomResponse.class)
                .getBody().getData();
    }

    private String getUrlWithParams(Map<String, Object> params, String requestUrl) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(requestUrl);
        if (params != null && !params.isEmpty()) {
            params.forEach(uriBuilder::queryParam);
        }
        return uriBuilder.toUriString();
    }
}
