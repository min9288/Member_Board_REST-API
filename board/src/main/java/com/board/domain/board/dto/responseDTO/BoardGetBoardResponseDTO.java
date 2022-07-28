package com.board.domain.board.dto.responseDTO;

import com.board.domain.board.entity.enumPackage.BoardStatus;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class BoardGetBoardResponseDTO {

    private String title;
    private String contents;
    private String writer;
    private int hit;
    private LocalDate enrollDate;
    private BoardStatus boardStatus;

    @Builder
    public BoardGetBoardResponseDTO(String title, String contents, String writer, int hit, LocalDate enrollDate,
            BoardStatus boardStatus) {
        this.title = title;
        this.contents = contents;
        this.writer = writer;
        this.hit = hit;
        this.enrollDate = enrollDate;
        this.boardStatus = boardStatus;
    }
}
