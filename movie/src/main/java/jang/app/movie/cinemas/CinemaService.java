package jang.app.movie.cinemas;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CinemaService {

    @Autowired
    private CinemaRepository cinemaRepository;

    public List<CinemaDTO> getFindCinema() {
        List<CinemaEntity> cinemaEntities  = cinemaRepository.findAll();
        List<CinemaDTO> cinemaDTOs = new ArrayList<>();

        for (CinemaEntity entity : cinemaEntities) {
            CinemaDTO dto = entity.toDTO();
            cinemaDTOs.add(dto);
        }
        return cinemaDTOs;
    }
}
