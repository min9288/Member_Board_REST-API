package com.board.domain.email.dto.requestDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmailAuthRequestDto {
    String email;
    String authToken;
}
