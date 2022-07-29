package com.board.domain.member.dto.requestDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberRegisterRequestDto {
    private String email;
    private String password;
    private String nickname;
}
