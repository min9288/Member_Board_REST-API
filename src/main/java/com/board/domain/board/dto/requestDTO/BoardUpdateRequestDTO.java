package com.board.domain.board.dto.requestDTO;

import com.board.domain.board.entity.Board;
import com.board.domain.board.entity.enumPackage.BoardStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BoardUpdateRequestDTO {
    private String title;
    private String contents;
    private BoardStatus boardStatus;

    public Board toEntity() {
        return new Board(title, contents, boardStatus);
    }
}
