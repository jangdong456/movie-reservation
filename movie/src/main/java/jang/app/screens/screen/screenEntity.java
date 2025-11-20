package jang.app.screens.screen;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class screenEntity {
    @Id @GeneratedValue
    private int screen_id;
    private int screen_number;
}
