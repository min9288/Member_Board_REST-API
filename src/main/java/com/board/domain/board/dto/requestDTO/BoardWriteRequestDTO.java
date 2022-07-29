package com.board.domain.board.dto.requestDTO;

import com.board.domain.board.entity.enumPackage.BoardStatus;
import com.board.domain.member.entity.Member;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BoardWriteRequestDTO {
    private String title;
    private String contents;
    private BoardStatus boardStatus;
}
