package com.polar_moviechart.userservice.domain.entity.movie;

import com.polar_moviechart.userservice.domain.entity.dto.MovieReviewRes;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "movie_reviews")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MovieReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Integer code;

    @Column(nullable = false)
    private String content;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;

    @Builder
    public MovieReview(Long userId, Integer code, String content) {
        this.userId = userId;
        this.code = code;
        this.content = content;
    }

    public MovieReviewRes toDto() {
        return MovieReviewRes.builder()
                .userId(userId)
                .code(this.code)
                .content(content)
                .createdAt(createdAt)
                .build();
    }
}
