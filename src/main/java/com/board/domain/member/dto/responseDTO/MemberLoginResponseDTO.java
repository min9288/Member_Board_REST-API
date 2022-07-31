package com.board.domain.member.dto.responseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberLoginResponseDTO {
    private UUID memberUUID;
    private String nickname;
    private String token;
    private String refreshToken;

}
