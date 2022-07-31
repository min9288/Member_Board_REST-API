package com.board.domain.email.dto.requestDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmailAuthRequestDTO {
    String email;
    String authToken;
}
