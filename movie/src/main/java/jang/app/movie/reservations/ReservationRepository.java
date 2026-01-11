package jang.app.movie.reservations;


import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {
    @Query(value = """
            select
            	m.title AS "영화제목",
                sum(r.total_price) AS "총 예매금액",
                count(mb.member_id) AS "총 예매자수"
                from
            		reservations r
            	inner join
            		movies m on r.movie_id = m.movie_id
            	inner join
            		members mb on r.member_id = mb.member_id
            	where
            		m.title = :title
            	group by
            		m.title
            """, nativeQuery = true )
    List<Object[]> findMovieReservation(
            @Param("title") String title
    );

    @Modifying
    @Query( value = """
            insert into reservation_stats(member_id, login_id, total_count, total_price, updated_at)
            select
                m.member_id,
                m.login_id,
                COUNT(m.member_id),
                SUM(r.total_price),
                NOW() 
            from reservations r
            join members m ON r.member_id = m.member_id
            group by m.member_id, m.login_id
            on duplicate key update
                   total_count = VALUES(total_count),
                   total_price = values(total_price),
                   updated_at = NOW()
            """, nativeQuery = true)
    void refreshReservationStats();
}
