package jang.app.movie.review;

import jakarta.persistence.*;
import jang.app.movie.members.MemberEntity;
import jang.app.movie.movies.MovieEntity;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.lang.reflect.Member;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "reviews")
@Builder
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class ReviewEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    @Column(name = "review_content", columnDefinition = "TEXT")
    private String reviewContent;

    @Column(name = "review_create")
    @CreatedDate
    private LocalDateTime createAt;

    // 1: N 관계 - members(1) : reviews(n)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;

    // 1: N 관계 - movies(1) : reviews(n)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    private MovieEntity movieEntity;

    public ReviewDTO toDTO() {
        return ReviewDTO.builder()
                .content(this.reviewContent)
                .build();
    }
}
