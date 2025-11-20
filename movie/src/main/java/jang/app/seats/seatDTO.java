package jang.app.seats;

import lombok.Data;

@Data
public class seatDTO {
    private int seat_number;

    public seatEntity toEntity() {
        return seatEntity.builder()
                .seat_number(this.seat_number)
                .build();
    }
}
