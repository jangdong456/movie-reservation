package jang.app.movie.tmdb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TmdbController {

    @Autowired
    private TmdbService tmdbService;

    @GetMapping("/movies/now-playing")
    public TmdbResponseDTO getNowPlayingMovies() {
        return tmdbService.getNowPlayingMovies();
    }
}
