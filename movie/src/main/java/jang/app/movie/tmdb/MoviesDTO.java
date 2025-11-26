package jang.app.movie.tmdb;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MoviesDTO {
    private Long id;
    private String title;
    private String poster_path;

    @JsonProperty("release_data")
    private String releaseDate;

    @JsonProperty("overview")
    private String overview;
}
