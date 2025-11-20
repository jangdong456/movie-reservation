package jang.app.cinemas;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class cinemaEntity {
    @Id
    @GeneratedValue
    private int cinema_id;
    private String cinema_name;
}
