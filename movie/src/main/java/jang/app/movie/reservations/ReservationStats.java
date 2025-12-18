package jang.app.movie.reservations;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "reservation_stats")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationStats {
    @Id
    private Long memberId;

    private String loginId;
    private long totalCount;
    private long totalPrice;
    private LocalDateTime updatedAt;
}
