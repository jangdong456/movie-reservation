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
    private int showtimeId;
    private int reservationId;
    private int seatId;

    @Builder
    public ReservationSeatId(int showtimeId, int reservationId, int seatId) {
        this.showtimeId = showtimeId;
        this.reservationId = reservationId;
        this.seatId = seatId;
    }
}
