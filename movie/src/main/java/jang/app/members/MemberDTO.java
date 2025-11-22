package jang.app.members;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
