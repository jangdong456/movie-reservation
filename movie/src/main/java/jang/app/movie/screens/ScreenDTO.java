package jang.app.movie.screens;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ScreenDTO {
    private String screenNumber;

    public ScreenEntity toEntity() {
        return ScreenEntity.builder()
                .screenNumber(this.screenNumber)
                .build();
    }
}
