package com.polar_moviechart.userservice.domain.service.movie;

import com.polar_moviechart.userservice.controller.internalapi.dtos.UserMoviesLikeReq;
import com.polar_moviechart.userservice.domain.entity.movie.MovieRating;
import com.polar_moviechart.userservice.repository.movie.MovieRatingRepository;
import com.polar_moviechart.userservice.domain.service.movie.dtos.MovieRatingRes;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieRatingQueryService {
    private final MovieRatingRepository movieRatingRepository;

    public Double getUserMovieRating(int code, Long userId) {
        return movieRatingRepository.findByCodeAndUserId(code, userId)
                .map(MovieRating::getRating)
                .orElse(0.0);
    }

    public List<MovieRatingRes> getUserMovieRatings(Long userId, PageRequest pageable) {
        return MovieRatingRes.listFrom(
                movieRatingRepository.findByUserId(userId, pageable));
    }

    public Page<MovieRatingRes> getUserMoviesRating(UserMoviesLikeReq userMoviesLikeReq, PageRequest pageable) {
        Page<MovieRating> moviesRating = movieRatingRepository.findByUserIdOrderByIdDesc(userMoviesLikeReq.getUserId(), pageable);
        List<MovieRatingRes> movieRatingRes = MovieRatingRes.listFrom(moviesRating.getContent());

        return new PageImpl<>(movieRatingRes, pageable, moviesRating.getTotalElements());
    }
}
