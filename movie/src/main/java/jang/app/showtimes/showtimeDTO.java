package jang.app.showtimes;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class showtimeDTO {
    private LocalDateTime showtime_data;
    private int price;

    public showtimeEntity toEntity() {
        return showtimeEntity.builder()
                .showtime_data(this.showtime_data)
                .price(this.price)
                .build();
    }
}
