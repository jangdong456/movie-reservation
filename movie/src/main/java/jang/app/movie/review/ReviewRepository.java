package jang.app.movie.review;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {

    @Query(value = """
            SELECT m.title, r.review_content, r.review_create FROM reviews r
            INNER JOIN movies m ON r.movie_id = m.movie_id
            INNER JOIN members mb ON r.member_id = mb.member_id
            WHERE mb.login_id = :loginId AND YEAR(r.review_create) = :year
            """, nativeQuery = true)
    List<Object[]> findUserReviewsNative(
            @Param("loginId") String loginId,
            @Param("year") int year
    );
}



