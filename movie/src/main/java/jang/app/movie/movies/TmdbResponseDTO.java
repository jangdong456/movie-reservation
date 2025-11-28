package jang.app.movie.movies;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class TmdbResponseDTO {
    private List<MovieDTO> results;
}
