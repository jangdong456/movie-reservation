package jang.app.movie.cinemas;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@Slf4j
public class CinemaController {

    @Autowired
    private CinemaService cinemaService;

//    @GetMapping("/")
//    public String getCinema(Model model) {
//        List<CinemaDTO> cinemaDTO = cinemaService.getFindCinema();
//        model.addAttribute("cinemaDTO", cinemaDTO);
//        return "nav.jsp";
//    }
}
