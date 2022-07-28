package com.board.domain.board.dto.requestDTO;

import com.board.domain.board.entity.enumPackage.BoardStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BoardUpdateRequestDTO {
    private String title;
    private String contents;
    private BoardStatus boardStatus;
}
