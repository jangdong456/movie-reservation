package jang.app.movie.reservations;

import jang.app.movie.members.MemberEntity;
import jang.app.movie.members.MemberRepository;
import jang.app.movie.movies.MovieEntity;
import jang.app.movie.movies.MovieRepository;
import jang.app.movie.seats.SeatRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@Slf4j
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ReservationStatsRepository reservationStatsRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private SeatRepository seatRepository;

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

        ReservationEntity reservationEntity = reservationDTO.toEntity(memberEntity, movieEntity,null);

        reservationRepository.save(reservationEntity);
    }

    public List<Object[]> findReservation(String movieTitle) {
        log.info("@@@@@@@시작@@@@@");
        List<Object[]> find = reservationRepository.findMovieReservation(movieTitle);
        log.info(">>>>>>>>>>> 결과물 : {} <<<<<<<<<<<", find);
        return find;
    }

    @Scheduled(cron = "0 */5 * * * *")
    @Transactional
    public void refreshReservation() {
        try {
            reservationRepository.refreshReservationStats();
            log.info("통계 테이블 업데이트 성공");
        } catch (Exception e) {
            log.error("통계테이블 업데이트 실패", e);
            throw e;
        }
    }

    public ReservationStats getTotal(String loginId) {
        return reservationStatsRepository.findByLoginId(loginId)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원의 통계 데이터가 존재하지 않습니다."));
    }

    public void postLock() {
        /*
        1.사용자 좌선 선택
            - 현재 상태가 "예매 가능" 인지 확인 한다.
                - 클라이언트에서 좌석 열과 번호를 받아 상태를 확인하면 된다.
            - "예매 가능" 이면 DB에서 해당 좌석을 비관적 락을 건다.
        2.좌석 상태를 결제진행 중 으로 업데이트 해야한다.
        3.이 때 선점 시간과 함께 기록하여, 다른 사람이 락을 풀고 들어왔을 때도
        "이미 결제 중인 좌석 입니다" 라는 메시지를 보여줍니다.
        */

        // seatRepository 에 좌석번호를 넘겨줘야함
//        seatRepository.findByIdWithPessimisticLock(32);

    }
}
