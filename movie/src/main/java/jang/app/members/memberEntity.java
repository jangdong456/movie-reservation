package jang.app.members;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class memberEntity {

    @Id @GeneratedValue
    private Long id;
    private String member_id;
    private int member_phone;
}
