package jang.app.reservations;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class ReservationDTO {

    private LocalDateTime reservationData;
    private int totalPrice;

    public ReservationEntity toEntity() {
        return ReservationEntity.builder()
                .reservationData(this.reservationData)
                .totalPrice(this.totalPrice)
                .build();
    }
}
