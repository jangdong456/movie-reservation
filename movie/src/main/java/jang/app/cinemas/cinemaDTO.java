package jang.app.cinemas;

import lombok.Data;

@Data
public class cinemaDTO {

    private String cinema_name;

    public cinemaEntity toEntity() {
        return cinemaEntity.builder()
                .cinema_name(this.cinema_name)
                .build();
    }
}
