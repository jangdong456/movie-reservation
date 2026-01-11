package jang.app.movie.seats;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SeatRepository extends JpaRepository<SeatEntity, Integer> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select s from SeatEntity s where s.seatsId = :id")
    // PessimisticLock -> for update 의미 : lock을 걸겠다.
    Optional<SeatEntity> findByIdWithPessimisticLock(Integer id);
}
