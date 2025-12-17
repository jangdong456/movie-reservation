package jang.app.movie.reservations;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

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
}
