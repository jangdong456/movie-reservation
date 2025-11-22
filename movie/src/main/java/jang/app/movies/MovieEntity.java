package jang.app.movies;

import jakarta.persistence.*;
import jang.app.showtimes.ShowtimeEntity;
import lombok.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "movies")
public class MovieEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movieId;

    @Column(name = "movie_title", nullable = false)
    private String movieTitle;

    @Column(name = "movie_time", nullable = false)
    private Duration movieTime;

    // 1:N 관계 movieEntity(1) : showtimeEntity(n)
    @OneToMany(mappedBy = "movieEntity")
    private List<ShowtimeEntity> showtimeEntities = new ArrayList<>();

    @Builder
    public MovieEntity(String movieTitle, Duration movieTime) {
        this.movieTitle = movieTitle;
        this.movieTime = movieTime;
    }

}
