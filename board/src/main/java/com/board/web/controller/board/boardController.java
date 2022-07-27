package com.board.web.controller.board;

import com.board.domain.board.dto.requestDTO.BoardWriteRequestDTO;
import com.board.domain.board.dto.responseDTO.BoardWriteResponseDTO;
import com.board.domain.board.service.BoardService;
import com.board.domain.response.service.ResponseService;
import com.board.domain.result.SingleResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class boardController {

    private final ResponseService responseService;
    private final BoardService boardService;

    // 게시글 작성
    @PostMapping("/write")
    public SingleResult<BoardWriteResponseDTO> insertBoard(@Valid @RequestBody BoardWriteRequestDTO requestDTO) {
        return responseService.getSingleResult(boardService.insertBoard(requestDTO));
    }

    // 게시글 전체 조회
    @GetMapping
    public String test(){
        return "good";
    }

    // 게시글 상세 조회 (비밀글 본인만 조회 가능)

    // 게시글 수정

    // 게시글 삭제
}
