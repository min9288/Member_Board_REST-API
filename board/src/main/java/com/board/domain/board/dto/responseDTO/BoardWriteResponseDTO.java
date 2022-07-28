package com.board.domain.board.dto.responseDTO;

import com.board.domain.board.entity.enumPackage.BoardStatus;
import com.board.domain.member.entity.Member;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
public class BoardWriteResponseDTO {

    private UUID boardUUID;
    private String title;
    private String contents;
    private String writer;
    private BoardStatus boardStatus;

    @Builder
    public BoardWriteResponseDTO(UUID boardUUID, String title, String contents, String writer, BoardStatus boardStatus) {
        this.boardUUID = boardUUID;
        this.title = title;
        this.contents = contents;
        this.writer = writer;
        this.boardStatus = boardStatus;
    }


}
