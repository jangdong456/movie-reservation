package jang.app.movies;

import lombok.Data;

import java.time.Duration;

@Data
public class movieDTO {

    private String movie_title;
    private Duration movie_time;

    public movieEntity toEntity() {
        return movieEntity.builder()
                .movie_title(this.movie_title)
                .movie_time(this.movie_time)
                .build();
    }
}
