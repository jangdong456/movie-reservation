package jang.app.movie.showtimes;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ShowtimeDTO {
    private LocalDateTime showtimeData;

    public ShowtimeEntity toEntity() {
        return ShowtimeEntity.builder()
                .showtimeData(this.showtimeData)
                .build();
    }
}
