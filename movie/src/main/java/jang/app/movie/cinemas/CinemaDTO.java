package jang.app.movie.cinemas;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CinemaDTO {

    private String cinemaName;

    // DTO -> Entity 전환
    public CinemaEntity toEntity() {
        return CinemaEntity.builder()
                .cinemaName(this.cinemaName)
                .build();
    }
}
