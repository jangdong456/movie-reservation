package jang.app.movie.tmdb;

import lombok.Data;

import java.util.List;

@Data
public class TmdbResponseDTO {
    private List<MoviesDTO> results;
}
