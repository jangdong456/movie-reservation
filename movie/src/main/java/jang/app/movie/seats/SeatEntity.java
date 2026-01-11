package jang.app.movie.seats;

import jakarta.persistence.*;
import jang.app.movie.reservations.ReservationEntity;
import jang.app.movie.screens.ScreenEntity;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "seats")
public class SeatEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer seatsId;

    @Column(name = "seat_number")
    private int seatNumber;

    @Column(name = "seat_status")
    private String seatStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "screen_id")
    private ScreenEntity screenEntity;

    // N:1 관계 seatEntity(n) : reservation(1)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_id")
    private ReservationEntity reservationEntity;

    @Builder
    public SeatEntity(int seatNumber, String seatStatus, ScreenEntity screenEntity) {
        this.seatNumber = seatNumber;
        this.seatStatus = seatStatus;
        this.screenEntity = screenEntity;
    }
}
