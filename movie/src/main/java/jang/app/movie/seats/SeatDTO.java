package jang.app.movie.seats;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SeatDTO {
    private int seatNumber;
    private String seatStatus;

    public SeatEntity toEntity() {
        return SeatEntity.builder()
                .seatNumber(this.seatNumber)
                .seatStatus(this.seatStatus)
                .build();
    }
}
