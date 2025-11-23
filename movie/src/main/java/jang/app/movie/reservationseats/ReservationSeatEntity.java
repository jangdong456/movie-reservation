package jang.app.movie.reservationseats;

import jakarta.persistence.*;
import jang.app.movie.reservations.ReservationEntity;
import jang.app.movie.seats.SeatEntity;
import jang.app.movie.showtimes.ShowtimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "reservation_seats")
public class ReservationSeatEntity {

    //복합키 매핑
    @EmbeddedId
    private ReservationSeatId reservationSeatId;

    @MapsId("reservationId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_id")
    private ReservationEntity reservationEntity;

    @MapsId("showtimeId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "showtime_id")
    private ShowtimeEntity showtimeEntity;

    @MapsId("seatId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seat_id")
    private SeatEntity seatEntity;

    @Builder
    public ReservationSeatEntity(
            ReservationSeatId reservationSeatId,
            ReservationEntity reservationEntity,
            ShowtimeEntity showtimeEntity,
            SeatEntity seatEntity
    ) {
        this.reservationSeatId = reservationSeatId;
        this.reservationEntity = reservationEntity;
        this.showtimeEntity = showtimeEntity;
        this.seatEntity = seatEntity;
    }

}
