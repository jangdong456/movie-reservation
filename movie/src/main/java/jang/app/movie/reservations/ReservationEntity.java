package jang.app.movie.reservations;

import jakarta.persistence.*;
import jang.app.movie.members.MemberEntity;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "reservations")
public class ReservationEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reservationId;

    @Column(name = "reservation_data", nullable = false)
    private LocalDateTime reservationData;

    @Column(name = "total_price", nullable = false)
    private int totalPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id") // FK - 외래키 지정 : member_id -> members 테이블의 fk 이다
    private MemberEntity memberEntity;

    @Builder
    public ReservationEntity(LocalDateTime reservationData, int totalPrice) {
        this.reservationData = reservationData;
        this.totalPrice = totalPrice;
    }
}
