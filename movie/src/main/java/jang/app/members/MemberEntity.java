package jang.app.members;

import jakarta.persistence.*;
import jang.app.reservations.ReservationEntity;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@Table(name = "members", uniqueConstraints = {
//        @UniqueConstraint(
//                name = "UQ_MEMBER_LOGIN_ID", // 유니크 이름 지정
//                columnNames = {"login_id"} // 적용할 DB 컬럼 이름
//        )
//})
public class MemberEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(name = "login_id", nullable = true, length = 50)
    private String loginId;

    @Column(name = "member_password", nullable = false)
    private String memberPassword;

    @Column(name = "member_phone", nullable = false)
    private int memberPhone;

    // 1:N 관계
    @OneToMany(mappedBy = "memberEntity")
    private List<ReservationEntity> reservationEntityList = new ArrayList<>();

    @Builder
    public MemberEntity(String loginId, String memberPassword, int memberPhone) {
        this.loginId = loginId;
        this.memberPassword = memberPassword;
        this.memberPhone = memberPhone;
    }
}
