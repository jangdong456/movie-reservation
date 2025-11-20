package jang.app.members;

import lombok.Data;

@Data
public class memberDTO {
    private String member_id;
    private int member_phone;

    public memberEntity toEntity() {
        return memberEntity.builder()
                .member_id(this.member_id)
                .member_phone(this.member_phone)
                .build();

    }
}
