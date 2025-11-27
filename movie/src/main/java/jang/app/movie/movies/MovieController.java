package jang.app.movie.movies;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@Slf4j
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/movies/now-playing")
    @ResponseBody
    public List<MovieDTO> getMovieData() {
        return movieService.getMovieData();
    }
}
