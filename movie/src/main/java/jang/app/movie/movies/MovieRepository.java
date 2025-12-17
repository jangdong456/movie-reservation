package jang.app.movie.movies;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, Long> {
    // 제목으로 영화 엔티티 한 개를 찾는 쿼리 메소드
    Optional<MovieEntity> findByTitle(String title);
}
