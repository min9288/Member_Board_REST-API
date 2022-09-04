package com.board.domain.member.dto.requestDTO;

import com.board.domain.member.entity.Member;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberUpdateRequestDTO {

    private String nickname;
    private int money;

    public Member toEntity() {
        return new Member(nickname, money);
    }

}
