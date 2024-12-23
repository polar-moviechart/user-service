package com.polar_moviechart.userservice.domain.service.movie;

import com.polar_moviechart.userservice.domain.entity.movie.MovieReview;
import com.polar_moviechart.userservice.domain.service.movie.dtos.MovieReviewRes;
import com.polar_moviechart.userservice.repository.movie.MovieReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieReviewQueryService {
    private final MovieReviewRepository movieReviewRepository;

    public Page<MovieReviewRes> getReviews(Long userId, int code, PageRequest pageRequest) {
        Page<MovieReview> movieReviews = movieReviewRepository.findByCodeOrderByCreatedAtDesc(code, pageRequest);
        List<MovieReviewRes> movieReviewRes = Optional.ofNullable(movieReviews.getContent())
                .map(MovieReviewRes::listFrom)
                .orElseGet(List::of);

        return new PageImpl<>(movieReviewRes, pageRequest, movieReviews.getTotalElements());
    }

    public Page<MovieReviewRes> getUserMovieReviews(Long userId, PageRequest pageable) {
        Page<MovieReview> movieReviews = movieReviewRepository.findByUser_IdOrderByCreatedAtDesc(userId, pageable);
        List<MovieReviewRes> movieReviewRes = Optional.ofNullable(movieReviews.getContent())
                .map(MovieReviewRes::listFrom)
                .orElseGet(List::of);
        return new PageImpl<>(movieReviewRes, pageable, movieReviews.getTotalElements());
    }

    public List<MovieReviewRes> getUserMovieReview(Long userId, Integer code) {
        List<MovieReview> userReviews = movieReviewRepository.findByUser_IdAndCode(userId, code);
        return MovieReviewRes.listFrom(userReviews);
    }
}
