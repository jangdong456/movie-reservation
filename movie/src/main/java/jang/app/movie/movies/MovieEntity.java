package jang.app.movie.movies;

import jakarta.persistence.*;
import jang.app.movie.reservations.ReservationEntity;
import jang.app.movie.review.ReviewEntity;
import jang.app.movie.showtimes.ShowtimeEntity;
import lombok.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "movies")
@Builder
@AllArgsConstructor
public class MovieEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movieId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "poster_path")
    private String posterPath;

    @Column(name = "overview", columnDefinition = "TEXT")
    private String overview;

    // 1:N 관계 movieEntity(1) : showtimeEntity(n)
    @OneToMany(mappedBy = "movieEntity")
    private List<ShowtimeEntity> showtimeEntityList = new ArrayList<>();

    // 1:N 관계 movieEntity(1) : reviews(n)
    @OneToMany(mappedBy = "movieEntity")
    private List<ReviewEntity> reviewEntityList = new ArrayList<>();

    // 1:N 관계 movieEntity(1) : reservations(n)
    @OneToMany(mappedBy = "movieEntity")
    private List<ReservationEntity> reservationEntityList = new ArrayList<>();

//    public MovieEntity(String title, Duration movieTime, String posterPath, String overview) {
//        this.title = title;
//        this.posterPath = posterPath;
//        this.overview = overview;
//    }

    // Entity -> DTO 전환
    public MovieDTO toDTO() {
        return MovieDTO.builder()
                .title(this.title)
                .posterPath(this.posterPath)
                .overview(this.overview)
                .build();
        }
}
