package com.board.domain.board.dto.responseDTO;

import com.board.domain.board.entity.Board;
import com.board.domain.board.entity.enumPackage.BoardStatus;
import com.board.domain.member.entity.Member;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
public class BoardGetBoardListResponseDTO {

    private UUID boardUUID;
    private String title;
    private String writer;
    private int hit;
    private LocalDate enrollDate;
    private BoardStatus boardStatus;

    public static BoardGetBoardListResponseDTO createDTO(Board board) {
        return BoardGetBoardListResponseDTO.builder()
                .boardUUID(board.getBoardUUID())
                .title(board.getTitle())
                .writer(board.getWriter().getNickname())
                .hit(board.getHit())
                .enrollDate(board.getEnrollDate())
                .boardStatus(board.getBoardStatus())
                .build();
    }

    @Builder
    public BoardGetBoardListResponseDTO(UUID boardUUID, String title, String writer, int hit, LocalDate enrollDate, BoardStatus boardStatus) {
        this.boardUUID = boardUUID;
        this.title = title;
        this.writer = writer;
        this.hit = hit;
        this.enrollDate = enrollDate;
        this.boardStatus = boardStatus;
    }
}
