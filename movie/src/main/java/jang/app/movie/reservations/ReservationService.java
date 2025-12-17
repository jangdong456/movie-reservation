package jang.app.movie.reservations;

import jang.app.movie.members.MemberEntity;
import jang.app.movie.members.MemberRepository;
import jang.app.movie.movies.MovieEntity;
import jang.app.movie.movies.MovieRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@Slf4j
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MovieRepository movieRepository;

    public void postReservation(ReservationDTO reservationDTO) {
        LocalDateTime truncatedDateTime = reservationDTO.getReservationData().truncatedTo(ChronoUnit.MINUTES);
        reservationDTO.setReservationData(truncatedDateTime);

        Long memberId = reservationDTO.getMemberId();
        if (memberId == null) {
            log.error("Member ID is NULL in DTO");
            throw new IllegalArgumentException("회원 ID가 누락되었습니다.");
        }
        MemberEntity memberEntity = memberRepository.getReferenceById(memberId);

        String movieTite = reservationDTO.getMovieTitle();
        MovieEntity movieEntity = movieRepository.findByTitle(movieTite)
                .orElseThrow(() -> new IllegalArgumentException("해당 영화를 찾을 수 없습니다"));

        ReservationEntity reservationEntity = reservationDTO.toEntity(memberEntity, movieEntity);

        reservationRepository.save(reservationEntity);
    }

    public void postSeat() {

    }

    public List<Object[]> findReservation(String movieTitle) {
        log.info("@@@@@@@시작@@@@@");
        List<Object[]> find = reservationRepository.findMovieReservation(movieTitle);
        log.info(">>>>>>>>>>> 결과물 : {} <<<<<<<<<<<", find);
        return find;
    }
}
