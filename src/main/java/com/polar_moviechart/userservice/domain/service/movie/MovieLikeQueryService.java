package com.polar_moviechart.userservice.domain.service.movie;

import com.polar_moviechart.userservice.controller.internalapi.dtos.UserMoviesLikeReq;
import com.polar_moviechart.userservice.domain.entity.movie.MovieLike;
import com.polar_moviechart.userservice.domain.service.movie.dtos.MovieLikesRes;
import com.polar_moviechart.userservice.repository.movie.MovieLikeRepository;
import com.polar_moviechart.userservice.domain.service.movie.dtos.MovieLikeRes;
import com.polar_moviechart.userservice.exception.ErrorCode;
import com.polar_moviechart.userservice.exception.UserBusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieLikeQueryService {
        private final MovieLikeRepository movieLikeRepository;

    public MovieLikeRes getLikeRes(Long userId, int code) {
        MovieLike movieLike = getLike(userId, code);
        return MovieLikeRes.builder()
                .userId(movieLike.getUserId())
                .code(movieLike.getCode())
                .isLike(movieLike.getLikeStatus())
                .build();
    }

    public MovieLike getLike(Long userId, int code) {
        return movieLikeRepository.findByUserIdAndCode(userId, code)
                .orElseThrow(() -> new UserBusinessException(ErrorCode.LIKE_NOT_EXIST));
    }

    public Page<MovieLikeRes> getUserMovieLikes(Long userId, PageRequest pageable) {
        Page<MovieLike> movieLikes = movieLikeRepository.findByUserId(userId, pageable);
        List<MovieLikeRes> movieLikeRes = MovieLikeRes.listFrom(movieLikes.getContent());

        return new PageImpl<>(movieLikeRes, pageable, movieLikes.getTotalElements());
    }

    public Integer getMovieLikes(int code) {
        return movieLikeRepository.countByCode(code);
    }

    public Page<MovieLikesRes> getUserMoviesLike(UserMoviesLikeReq userMoviesLikeReq, PageRequest pageable) {
        if (userMoviesLikeReq.isMovieCodesEmpty()) {
            Page<MovieLike> movieLikes = movieLikeRepository.findByUserIdAndLikeStatus(userMoviesLikeReq.getUserId(), true, pageable);
            List<MovieLikesRes> movieLikeRes = MovieLikesRes.listFrom(movieLikes.getContent());
            return new PageImpl<>(movieLikeRes, pageable, movieLikes.getTotalElements());
        } else {
            Page<MovieLike> movieLikes = movieLikeRepository.findByUserIdAndCodeIn(userMoviesLikeReq.getUserId(), userMoviesLikeReq.getMovieCodes(), pageable);
            List<MovieLikesRes> movieLikeRes = MovieLikesRes.listFrom(movieLikes.getContent());
            return new PageImpl<>(movieLikeRes, pageable, movieLikes.getTotalElements());
        }
    }

    public Boolean getUserMovieLike(Long userId, Integer code) {
        Optional<MovieLike> likeOptional = movieLikeRepository.findByUserIdAndCode(userId, code);
        if (likeOptional.isEmpty()) {
            return false;
        }
        return likeOptional.get().getLikeStatus();
    }
}
