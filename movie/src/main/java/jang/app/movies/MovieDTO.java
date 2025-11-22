package jang.app.movies;

import lombok.Builder;
import lombok.Data;

import java.time.Duration;

@Data
@Builder
public class MovieDTO {

    private String movieTitle;
    private Duration movieTime;

    public MovieEntity toEntity() {
        return MovieEntity.builder()
                .movieTitle(this.movieTitle)
                .movieTime(this.movieTime)
                .build();
    }
}
