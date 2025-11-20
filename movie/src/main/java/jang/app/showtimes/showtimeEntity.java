package jang.app.showtimes;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class showtimeEntity {
    @Id @GeneratedValue
    private Long showtime_id;
    private LocalDateTime showtime_data;
    private int price;
}
