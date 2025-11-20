package jang.app.reservations;

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
public class reservationEntity {
    @Id @GeneratedValue
    private Long reservation_id;
    private LocalDateTime reservation_data;
    private int total_price;
}
