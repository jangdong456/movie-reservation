package jang.app.movie.cinemas;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CinemaService {

    @Autowired
    private CinemaRepository cinemaRepository;

    public void getFindCinema() {
        List<CinemaEntity> cinemaEntities  = cinemaRepository.findAll();

    }
}
