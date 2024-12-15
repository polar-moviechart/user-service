package com.polar_moviechart.userservice.domain.service.movie;


import com.polar_moviechart.userservice.controller.secureapi.dtos.UpdateMovieLikeReq;
import com.polar_moviechart.userservice.domain.entity.User;
import com.polar_moviechart.userservice.domain.entity.movie.MovieLike;
import com.polar_moviechart.userservice.repository.movie.MovieLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MovieLikeCommandService {
    private final MovieLikeRepository movieLikeRepository;
    @Transactional
    public MovieLike updateLike(int code, User user, UpdateMovieLikeReq req) {
        MovieLike movieLike = movieLikeRepository.findByCodeAndUserId(code, user.getId())
                .orElseGet(() -> MovieLike.builder()
                        .userId(user.getId())
                        .code(code)
                        .likeStatus(req.getIsLike())
                        .build());

        movieLike.setLikeStatus(req.getIsLike());
        return movieLikeRepository.save(movieLike);
    }
}
