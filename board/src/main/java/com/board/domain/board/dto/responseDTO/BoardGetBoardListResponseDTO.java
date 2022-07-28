package com.board.domain.board.dto.responseDTO;

import com.board.domain.board.entity.Board;
import com.board.domain.member.entity.Member;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class BoardGetBoardListResponseDTO {

    private String title;
    private String writer;
    private int hit;
    private LocalDate enrollDate;

    public static BoardGetBoardListResponseDTO createDTO(Board board) {
        return BoardGetBoardListResponseDTO.builder()
                .title(board.getTitle())
                .writer(board.getWriter().getNickname())
                .hit(board.getHit())
                .enrollDate(board.getEnrollDate())
                .build();
    }

    @Builder
    public BoardGetBoardListResponseDTO(String title, String writer, int hit, LocalDate enrollDate) {
        this.title = title;
        this.writer = writer;
        this.hit = hit;
        this.enrollDate = enrollDate;
    }
}
