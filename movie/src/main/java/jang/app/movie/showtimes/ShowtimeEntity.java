package jang.app.movie.showtimes;

import jakarta.persistence.*;
import jang.app.movie.movies.MovieEntity;
import jang.app.movie.screens.ScreenEntity;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "showtimes")
public class ShowtimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer showtimeId;

    @Column(name = "showtime_data", nullable = false)
    private LocalDateTime showtimeData;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    private MovieEntity movieEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "screen_id")
    private ScreenEntity screenEntity;

    @Builder
    public ShowtimeEntity(LocalDateTime showtimeData, MovieEntity movieEntity, ScreenEntity screenEntity) {
        this.showtimeData = showtimeData;
        this.movieEntity = movieEntity;
        this.screenEntity = screenEntity;
    }
}
