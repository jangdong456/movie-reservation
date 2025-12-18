package jang.app.movie.reservations;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReservationStatsRepository extends JpaRepository<ReservationStats, Long> {
    Optional<ReservationStats> findByLoginId(String loginId);
}
