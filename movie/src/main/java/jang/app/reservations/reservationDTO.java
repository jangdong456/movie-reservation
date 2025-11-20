package jang.app.reservations;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class reservationDTO {

    private LocalDateTime reservation_data;
    private int total_price;

    public reservationEntity toEntity() {
        return reservationEntity.builder()
                .reservation_data(this.reservation_data)
                .total_price(this.total_price)
                .build();
    }
}
