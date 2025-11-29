package jang.app.movie.reservationseats;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode
public class ReservationSeatId implements Serializable {

    //복합키
    private Integer showtimeId;
    private Long reservationId;
    private Integer seatId;

    @Builder
    public ReservationSeatId(Integer showtimeId, Long reservationId, Integer seatId) {
        this.showtimeId = showtimeId;
        this.reservationId = reservationId;
        this.seatId = seatId;
    }
}
