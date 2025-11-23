package jang.app.movie.seats;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SeatDTO {
    private int seatNumber;

    public SeatEntity toEntity() {
        return SeatEntity.builder()
                .seatNumber(this.seatNumber)
                .build();
    }
}
