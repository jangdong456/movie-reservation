package jang.app.movie.cinemas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CinemaRepository extends JpaRepository<CinemaEntity, Integer> {
    List<CinemaEntity> findByCinemaName(String name);
}
