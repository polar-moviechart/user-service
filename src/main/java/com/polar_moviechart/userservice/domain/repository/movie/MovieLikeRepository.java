package com.polar_moviechart.userservice.domain.repository.movie;

import com.polar_moviechart.userservice.domain.entity.movie.MovieLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieLikeRepository extends JpaRepository<MovieLike, Long> {
}
