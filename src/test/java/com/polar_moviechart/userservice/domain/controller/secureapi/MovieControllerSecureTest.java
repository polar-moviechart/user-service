package com.polar_moviechart.userservice.domain.controller.secureapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.polar_moviechart.userservice.controller.secureapi.MovieControllerSecure;
import com.polar_moviechart.userservice.controller.secureapi.dtos.UpdateMovieReviewReq;
import com.polar_moviechart.userservice.event.MovieEventPublisher;
import com.polar_moviechart.userservice.event.MovieLikeEventPublisher;
import com.polar_moviechart.userservice.domain.service.movie.MovieCommandService;
import com.polar_moviechart.userservice.domain.service.movie.MovieQueryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MovieControllerSecure.class)
class MovieControllerSecureTest {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @MockBean private MovieQueryService movieQueryService;
    @MockBean private MovieCommandService movieCommandService;
    @MockBean private MovieEventPublisher movieEventPublisher;

    @Test
    void addReview_InvalidRequest_ReturnsBadRequest() throws Exception {
        String content = "";
        UpdateMovieReviewReq updateMovieReviewReq = new UpdateMovieReviewReq(content);
        String requestBody = objectMapper.writeValueAsString(updateMovieReviewReq);

        mockMvc.perform(post("/secure/api/v1/users/movies/123/reviews")
                        .header("X-User-Id", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorMsg").value("한 글자 이상 리뷰를 작성해주세요."));
    }
}
