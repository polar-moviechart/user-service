package com.polar_moviechart.userservice.domain.entity.movie;

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
@Table(name = "movie_likes")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class MovieLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "code", nullable = false)
    private Integer code;

    @Column(name = "is_like", nullable = false)
    private Boolean likeStatus;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Builder
    public MovieLike(Long userId, Integer code, Boolean likeStatus) {
        this.userId = userId;
        this.code = code;
        this.likeStatus = likeStatus;
    }

    public void setLikeStatus(Boolean likeStatus) {
        this.likeStatus = likeStatus;
    }
}
