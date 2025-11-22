package jang.app.seats;

import jakarta.persistence.*;
import jang.app.screens.ScreenEntity;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SeatEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seatsId;

    @Column(name = "seat_number", nullable = false)
    private int seatNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "screen_id")
    private ScreenEntity screenEntity;

    @Builder
    public SeatEntity(int seatNumber, ScreenEntity screenEntity) {
        this.seatNumber = seatNumber;
        this.screenEntity = screenEntity;
    }
}
