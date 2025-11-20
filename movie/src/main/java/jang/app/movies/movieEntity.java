package jang.app.movies;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import java.time.Duration;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class movieEntity {

    @Id @GeneratedValue
    private Long movie_id;
    private String movie_title;
    private Duration movie_time;

}
