package jang.app.movie.members;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberDTO {
    private String loginId;
    private String memberPassword;
    private int memberPhone;

    public MemberEntity toEntity() {
        return MemberEntity.builder()
                .loginId(this.loginId)
                .memberPassword(this.memberPassword)
                .memberPhone(this.memberPhone)
                .build();

    }
}
