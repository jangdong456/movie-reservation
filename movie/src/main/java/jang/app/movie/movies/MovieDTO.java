package jang.app.movie.movies;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.time.Duration;

@Data
@Builder
public class MovieDTO {

    private String title;
    private Duration movieTime;

    @JsonProperty("poster_path")
    private String posterPath;
    private String overview;

    public MovieEntity toEntity() {
        return MovieEntity.builder()
                .title(this.title)
                .movieTime(this.movieTime)
                .posterPath(this.posterPath)
                .overview(this.overview)
                .build();
    }
}
