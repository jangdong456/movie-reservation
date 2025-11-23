package jang.app.movie.reservationseats;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReservationSeatDTO {

    // 복합키를 이루는 세 필드를 DTO의 필드로 사용
    private int showtimeId;
    private int reservationId;
    private int seatId;

    public ReservationSeatEntity toEntity() {
        ReservationSeatId complexId = ReservationSeatId.builder()
                .showtimeId(this.showtimeId)
                .reservationId(this.reservationId)
                .seatId(this.seatId)
                .build();

        return ReservationSeatEntity.builder()
                .reservationSeatId(complexId)
                .build();
    }
}
