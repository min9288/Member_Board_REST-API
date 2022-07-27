package com.board.domain.board.dto.responseDTO;

import com.board.domain.board.entity.enumPackage.BoardStatus;
import com.board.domain.member.entity.Member;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class BoardWriteResponseDTO {
    private String title;
    private String contents;
    private Member writer;
    private BoardStatus boardStatus;
    private LocalDate enrollDate;

    @Builder
    public BoardWriteResponseDTO(String title, String contents, Member writer, BoardStatus boardStatus,
                                 LocalDate enrollDate) {
        this.title = title;
        this.contents = contents;
        this.writer = writer;
        this.boardStatus = boardStatus;
        this.enrollDate = enrollDate;
    }


}
