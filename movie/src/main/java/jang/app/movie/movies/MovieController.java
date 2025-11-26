package jang.app.movie.movies;

import jang.app.movie.tmdb.RestTemplateConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class MovieController {

    @Autowired
    private RestTemplateConfig restTemplate;

}
