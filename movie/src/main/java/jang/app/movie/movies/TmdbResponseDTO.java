package jang.app.movie.movies;

import lombok.Data;

import java.util.List;

@Data
public class TmdbResponseDTO {
    private List<MovieDTO> results;
}
