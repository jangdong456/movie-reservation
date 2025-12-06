package jang.app.movie.reservations;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class ReservationRequest {
    // 1. 핵심 DB 정보 (FK로 사용될 정보)
    private String showtimeId;
    private String memberId;

    // 2. 가격 및 인원/좌석 정보 (비즈니스 로직에 필요)
    private int totalPrice;
    private Map<String, Integer> peopleCounts;
    private List<String> selectedSeats;

    // 3. 로그 및 확인용 정보
    private String cinema;
    private String movieTitle;
    private String screen;
}
