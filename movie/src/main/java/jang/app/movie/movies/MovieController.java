package jang.app.movie.movies;

import jang.app.movie.cinemas.CinemaDTO;
import jang.app.movie.cinemas.CinemaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@Slf4j
public class MovieController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private CinemaService cinemaService;

    @GetMapping("/movies/now-playing")
    @ResponseBody
    public List<MovieDTO> getMovieData() {
        return movieService.getMovieData();
    }

    @GetMapping("/")
    public String index(Model model) {
        List<MovieDTO> movieDto = movieService.getIndexPage();
        model.addAttribute("movieDto", movieDto);

        List<CinemaDTO> cinemaDTOs = cinemaService.getFindCinema();
        model.addAttribute("cinemaDTO", cinemaDTOs);
        return "index";
    }

    @GetMapping("/reservation")
    public String getReservaiotn(Model model) {
        List<MovieDTO> movieDto = movieService.getIndexPage();
        model.addAttribute("movieDto", movieDto);

        List<CinemaDTO> cinemaDto = movieService.getCinemaName();
        model.addAttribute("cinemaDto", cinemaDto);
        return "reservation/bookingModal";
    }
}
