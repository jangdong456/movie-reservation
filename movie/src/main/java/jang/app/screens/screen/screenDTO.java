package jang.app.screens.screen;

import lombok.Data;

@Data
public class screenDTO {
    private int screen_number;

    public screenEntity toEntity() {
        return screenEntity.builder()
                .screen_number(this.screen_number)
                .build();
    }
}
