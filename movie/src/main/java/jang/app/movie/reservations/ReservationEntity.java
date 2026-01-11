package jang.app.movie.reservations;

import jakarta.persistence.*;
import jang.app.movie.members.MemberEntity;
import jang.app.movie.movies.MovieEntity;
import jang.app.movie.seats.SeatEntity;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "reservations")
public class ReservationEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationId;

    @Column(name = "reservation_data", nullable = false)
    private LocalDateTime reservationData;

    @Column(name = "total_price", nullable = false)
    private int totalPrice;

    // 1:N 관계 memberEntity(1) : reservations(n)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id") // FK - 외래키 지정 : member_id -> members 테이블의 fk 이다
    private MemberEntity memberEntity;

    // 1:N 관계 movieEntity(1) : reservations(n)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    private MovieEntity movieEntity;

    // N:1 관계 seat(n) : reservations(1)
    @OneToMany(mappedBy = "reservationEntity")
    private  List<SeatEntity> seatEntityList = new ArrayList<>();

    @Builder
    public ReservationEntity(
            LocalDateTime reservationData, int totalPrice,
            MemberEntity memberEntity, MovieEntity movieEntity, List<SeatEntity> seatEntity
    ) {
        this.reservationData = reservationData;
        this.totalPrice = totalPrice;
        this.memberEntity = memberEntity;
        this.movieEntity = movieEntity;
        this.seatEntityList = seatEntity;
    }
}
