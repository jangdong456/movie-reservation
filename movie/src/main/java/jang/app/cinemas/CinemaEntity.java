package jang.app.cinemas;

import jakarta.persistence.*;
import jang.app.screens.ScreenEntity;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "cinemas")
public class CinemaEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cinemaId;

    @Column(name = "cinema_name", nullable = false)
    private String cinemaName;

    // 1:N 관계 cinemaEntity(1) : screenEntity(n)
    @OneToMany(mappedBy = "cinemaEntity")
    private List<ScreenEntity> screenEntityList = new ArrayList<>();

    @Builder
    public CinemaEntity(String cinemaName) {
        this.cinemaName = cinemaName;
    }
}
