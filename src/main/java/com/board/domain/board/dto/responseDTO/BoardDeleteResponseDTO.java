package com.board.domain.board.dto.responseDTO;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class BoardDeleteResponseDTO {

    private String successMSG;

    @Builder
    public BoardDeleteResponseDTO(String successMSG) {

        this.successMSG = successMSG;
    }
}
