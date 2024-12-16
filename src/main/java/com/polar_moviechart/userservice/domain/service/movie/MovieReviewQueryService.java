package com.polar_moviechart.userservice.domain.service.movie;

import com.polar_moviechart.userservice.domain.entity.movie.MovieReview;
import com.polar_moviechart.userservice.domain.service.movie.dtos.MovieReviewRes;
import com.polar_moviechart.userservice.repository.movie.MovieReviewRepository;
import com.polar_moviechart.userservice.exception.ErrorCode;
import com.polar_moviechart.userservice.exception.UserBusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieReviewQueryService {
    private final MovieReviewRepository movieReviewRepository;

    public Page<MovieReviewRes> getReviews(Long userId, int code, PageRequest pageRequest) {
        Page<MovieReview> movieReviews = movieReviewRepository.findByCodeOrderByCreatedAtDesc(code, pageRequest);
        List<MovieReviewRes> movieReviewRes = MovieReviewRes.listFrom(movieReviews.getContent());

        return new PageImpl<>(movieReviewRes, pageRequest, movieReviews.getTotalElements());
    }

    public List<MovieReviewRes> getUserMovieReviews(Long userId, PageRequest pageable) {
        List<MovieReview> movieReviews = movieReviewRepository.findByUser_Id(userId, pageable);
        if (movieReviews.isEmpty()) {
            throw new UserBusinessException(ErrorCode.REVIEW_NOT_EXISTS);
        }
        return MovieReviewRes.listFrom(movieReviews);
    }

    public List<MovieReviewRes> getUserMovieReview(Long userId, Integer code) {
        List<MovieReview> userReviews = movieReviewRepository.findByUser_IdAndCode(userId, code);
        return MovieReviewRes.listFrom(userReviews);
    }
}
