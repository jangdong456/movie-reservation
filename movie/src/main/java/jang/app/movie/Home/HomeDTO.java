package jang.app.movie.Home;

import lombok.Data;

@Data
public class HomeDTO {
    private String username;

    public Home toEntity() {
        return Home.builder()
                .username(this.username)
                .build();
    }
}
