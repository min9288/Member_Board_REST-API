package com.board.domain.board.dto.responseDTO;

import com.board.domain.board.entity.enumPackage.BoardStatus;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
public class BoardUpdateResponseDTO {

    private UUID boardUUID;
    private String title;
    private String contents;
    private String writer;
    private BoardStatus boardStatus;
    private LocalDate updateDate;

    @Builder
    public BoardUpdateResponseDTO(UUID boardUUID, String title, String contents, String writer,
                                  BoardStatus boardStatus, LocalDate updateDate) {
        this.boardUUID = boardUUID;
        this.title = title;
        this.contents = contents;
        this.writer = writer;
        this.boardStatus = boardStatus;
        this.updateDate = updateDate;
    }

}
