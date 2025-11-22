package jang.app.showtimes;

import jakarta.persistence.*;
import jang.app.movies.MovieEntity;
import jang.app.screens.ScreenEntity;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "showtimes")
public class ShowtimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int showtimeId;

    @Column(name = "showtime_data", nullable = false)
    private LocalDateTime showtimeData;

    @Column(name = "price", nullable = false)
    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    private MovieEntity movieEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "screen_id")
    private ScreenEntity screenEntity;

    @Builder
    public ShowtimeEntity(LocalDateTime showtimeData, int price, MovieEntity movieEntity, ScreenEntity screenEntity) {
        this.showtimeData = showtimeData;
        this.price = price;
        this.movieEntity = movieEntity;
        this.screenEntity = screenEntity;
    }
}
