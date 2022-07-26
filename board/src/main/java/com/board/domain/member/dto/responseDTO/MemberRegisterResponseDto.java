package com.board.domain.member.dto.responseDTO;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class MemberRegisterResponseDto {
    private UUID memberUUID;
    private String email;
    private String authToken;

    @Builder
    public MemberRegisterResponseDto(UUID memberUUID, String email, String authToken, String nickname) {
        this.memberUUID = memberUUID;
        this.email = email;
        this.authToken = authToken;
    }
}
