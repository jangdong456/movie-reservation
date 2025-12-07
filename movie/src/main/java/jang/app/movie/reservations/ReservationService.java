package jang.app.movie.reservations;

import jang.app.movie.members.MemberEntity;
import jang.app.movie.members.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
@Slf4j
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private MemberRepository memberRepository;

    public void postReservation(ReservationDTO reservationDTO) {
        LocalDateTime truncatedDateTime = reservationDTO.getReservationData().truncatedTo(ChronoUnit.MINUTES);
        reservationDTO.setReservationData(truncatedDateTime);

        Long memberId = reservationDTO.getMemberId();
        if (memberId == null) {
            log.error("Member ID is NULL in DTO");
            throw new IllegalArgumentException("회원 ID가 누락되었습니다.");
        }
        MemberEntity memberEntity = memberRepository.getReferenceById(memberId);

        ReservationEntity reservationEntity = reservationDTO.toEntity(memberEntity);

        reservationRepository.save(reservationEntity);
    }

    public void postSeat() {

    }
}
