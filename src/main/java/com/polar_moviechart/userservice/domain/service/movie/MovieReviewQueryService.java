package com.polar_moviechart.userservice.domain.service.movie;

import com.polar_moviechart.userservice.domain.entity.MovieReview;
import com.polar_moviechart.userservice.domain.entity.dto.MovieReviewRes;
import com.polar_moviechart.userservice.domain.repository.movie.MovieReviewRepository;
import com.polar_moviechart.userservice.exception.ErrorCode;
import com.polar_moviechart.userservice.exception.UserBusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieReviewQueryService {
    private final MovieReviewRepository movieReviewRepository;

    public MovieReviewRes getReview(Long userId, int code) {
        Optional<MovieReview> reviewOptional = movieReviewRepository
                .findByUserIdAndCode(userId, code);
        MovieReview movieReview = reviewOptional
                .orElseThrow(() -> new UserBusinessException(ErrorCode.REVIEW_NOT_EXISTS));
        return movieReview.toDto();
    }
}
