package jang.app.movie.reservations;

import jang.app.movie.members.MemberEntity;
import jang.app.movie.movies.MovieEntity;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class ReservationDTO {

    private LocalDateTime reservationData;
    private int totalPrice;
    private Long memberId;
    private String movieTitle;

    public ReservationEntity toEntity(MemberEntity memberEntity, MovieEntity movieEntity) {
        return ReservationEntity.builder()
                .reservationData(this.reservationData)
                .totalPrice(this.totalPrice)
                .memberEntity(memberEntity)
                .movieEntity(movieEntity)
                .build();
    }
}
