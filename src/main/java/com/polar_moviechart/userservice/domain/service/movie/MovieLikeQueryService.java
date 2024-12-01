package com.polar_moviechart.userservice.domain.service.movie;

import com.polar_moviechart.userservice.domain.entity.movie.MovieLike;
import com.polar_moviechart.userservice.repository.movie.MovieLikeRepository;
import com.polar_moviechart.userservice.domain.service.movie.dtos.MovieLikeRes;
import com.polar_moviechart.userservice.exception.ErrorCode;
import com.polar_moviechart.userservice.exception.UserBusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieLikeQueryService {
        private final MovieLikeRepository movieLikeRepository;

        public MovieLikeRes getLikeRes(Long userId, int code) {
            MovieLike movieLike = getLike(userId, code);
            return MovieLikeRes.builder()
                    .userId(movieLike.getUserId())
                    .code(movieLike.getCode())
                    .isLike(movieLike.getIsLike())
                    .build();
        }

        public MovieLike getLike(Long userId, int code) {
            return movieLikeRepository.findByUserIdAndCode(userId, code)
                    .orElseThrow(() -> new UserBusinessException(ErrorCode.LIKE_NOT_EXIST));
        }

    public List<MovieLikeRes> getUserMovieLikes(Long userId, PageRequest pageable) {
        List<MovieLike> movieLikes = movieLikeRepository.findByUserId(userId, pageable);
        return MovieLikeRes.listFrom(movieLikes);
    }

    public Integer getMovieLikes(int code) {
        return movieLikeRepository.countByCode(code);
    }
}
