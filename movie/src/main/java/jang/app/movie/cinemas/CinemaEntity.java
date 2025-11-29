package jang.app.movie.cinemas;

import jakarta.persistence.*;
import jang.app.movie.screens.ScreenEntity;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "cinemas")
public class CinemaEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cinemaId;

    @Column(name = "cinema_name", nullable = false)
    private String cinemaName;

    // 1:N 관계 cinemaEntity(1) : screenEntity(n)
    @OneToMany(mappedBy = "cinemaEntity")
    private List<ScreenEntity> screenEntityList = new ArrayList<>();


//    public CinemaEntity(String cinemaName) {
//        this.cinemaName = cinemaName;
//    }

    // Entity -> DTO 전환
    public CinemaDTO toDTO() {
        return CinemaDTO.builder()
                .cinemaName(this.cinemaName)
                .build();
    }
}
