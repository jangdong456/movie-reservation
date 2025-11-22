package jang.app.cinemas;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CinemaDTO {

    private String cinemaName;

    public CinemaEntity toEntity() {
        return CinemaEntity.builder()
                .cinemaName(this.cinemaName)
                .build();
    }
}
