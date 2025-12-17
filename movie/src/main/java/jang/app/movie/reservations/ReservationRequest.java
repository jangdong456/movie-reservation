package jang.app.movie.reservations;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationRequest {
    // 1. 핵심 DB 정보 (FK로 사용될 정보)
    private String showtimeId;
    private Long memberId; // MemberDTO

    // 2. 가격 및 인원/좌석 정보 (비즈니스 로직에 필요)
    private int totalPrice; // ReservationDTO
    private Map<String, Integer> peopleCounts; //
    private List<String> selectedSeats;

    // 3. 로그 및 확인용 정보 : movieDTO 에 저장하기
    private String cinema;
    private String movieTitle;
    private String screen;

    // 4. 날짜 정보 ReservationDTO
    private LocalDateTime currentDateTime;

    public ReservationDTO toDTO() {
        return ReservationDTO.builder()
                .totalPrice(this.totalPrice)
                .reservationData(this.currentDateTime)
                .memberId(this.memberId)
                .movieTitle(this.movieTitle)
                .build();
    }
}
