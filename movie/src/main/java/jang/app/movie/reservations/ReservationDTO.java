package jang.app.movie.reservations;

import jang.app.movie.members.MemberEntity;
import jang.app.movie.movies.MovieEntity;
import jang.app.movie.seats.SeatEntity;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class ReservationDTO {

    private LocalDateTime reservationData;
    private int totalPrice;
    private Long memberId;
    private String movieTitle;

    public ReservationEntity toEntity(MemberEntity memberEntity, MovieEntity movieEntity, List<SeatEntity> seatEntity) {
        return ReservationEntity.builder()
                .reservationData(this.reservationData)
                .totalPrice(this.totalPrice)
                .memberEntity(memberEntity)
                .movieEntity(movieEntity)
                .seatEntity(seatEntity)
                .build();
    }
}
