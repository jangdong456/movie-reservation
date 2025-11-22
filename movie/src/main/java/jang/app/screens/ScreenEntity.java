package jang.app.screens;

import jakarta.persistence.*;
import jang.app.cinemas.CinemaEntity;
import jang.app.seats.SeatEntity;
import jang.app.showtimes.ShowtimeEntity;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "screens")
public class ScreenEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int screenId;

    @Column(name = "screens", nullable = false)
    private int screenNumber;

    // N:1 관계 (N: Screen, 1: Cinema)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cinema_id")
    private CinemaEntity cinemaEntity;

    // 1:N 관계 (1: Screen, N: Seat)
    @OneToMany(mappedBy = "screenEntity")
    private List<SeatEntity> seatEntityList = new ArrayList<>();

    // 1:N 관계 (1: Screen, N: Showtime)
    @OneToMany(mappedBy = "screenEntity")
    private List<ShowtimeEntity> showtimeEntityList  = new ArrayList<>();

    @Builder
    public ScreenEntity(int screenNumber, CinemaEntity cinemaEntity) {
        this.screenNumber = screenNumber;
        this.cinemaEntity = cinemaEntity;
    }
}
