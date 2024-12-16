package com.polar_moviechart.userservice.repository.movie;

import com.polar_moviechart.userservice.domain.entity.movie.MovieReview;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieReviewRepository extends JpaRepository<MovieReview, Long> {
    Page<MovieReview> findByUser_IdOrderByCreatedAtDesc(Long userId, PageRequest pageable);

    List<MovieReview> findByUser_IdAndCode(Long userId, int code);

    Page<MovieReview> findByCodeOrderByCreatedAtDesc(int code, PageRequest pageRequest);
}
