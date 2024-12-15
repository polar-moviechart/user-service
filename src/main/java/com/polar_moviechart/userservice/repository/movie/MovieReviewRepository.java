package com.polar_moviechart.userservice.repository.movie;

import com.polar_moviechart.userservice.domain.entity.movie.MovieReview;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieReviewRepository extends JpaRepository<MovieReview, Long> {
    List<MovieReview> findByUser_Id(Long userId, PageRequest pageable);

    List<MovieReview> findByUser_IdAndCode(Long userId, int code);

    List<MovieReview> findByCodeOrderByCreatedAtDesc(int code, PageRequest pageRequest);
}
