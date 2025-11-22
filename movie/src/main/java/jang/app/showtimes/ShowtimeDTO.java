package jang.app.showtimes;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ShowtimeDTO {
    private LocalDateTime showtimeData;
    private int price;

    public ShowtimeEntity toEntity() {
        return ShowtimeEntity.builder()
                .showtimeData(this.showtimeData)
                .price(this.price)
                .build();
    }
}
