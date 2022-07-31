package com.board.domain.member.dto.requestDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TokenRequestDTO {
    String accessToken;
    String refreshToken;
}
