package jang.app.movie.reservations;

import jang.app.movie.members.MemberEntity;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class ReservationDTO {

    private LocalDateTime reservationData;
    private int totalPrice;
    private Long memberId;

    public ReservationEntity toEntity(MemberEntity memberEntity) {
        return ReservationEntity.builder()
                .reservationData(this.reservationData)
                .totalPrice(this.totalPrice)
                .memberEntity(memberEntity)
                .build();
    }

}
