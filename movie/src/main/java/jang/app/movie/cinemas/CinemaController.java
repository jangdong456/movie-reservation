package jang.app.movie.cinemas;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class CinemaController {

    @Autowired
    private CinemaService cinemaService;

    public void getCinema() {
        cinemaService.getFindCinema();
    }
}
