package com.board.web.controller.board;

import com.board.domain.board.dto.requestDTO.BoardUpdateRequestDTO;
import com.board.domain.board.dto.requestDTO.BoardWriteRequestDTO;
import com.board.domain.board.dto.responseDTO.*;
import com.board.domain.board.service.BoardService;
import com.board.domain.board.service.BoardServiceImpl;
import com.board.domain.response.service.ResponseService;
import com.board.domain.result.MultipleResult;
import com.board.domain.result.SingleResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/boards")
public class BoardController {

    private final ResponseService responseService;
    private final BoardServiceImpl boardService;

    // 게시글 작성
    @PostMapping
    public SingleResult<BoardWriteResponseDTO> insertBoard(@RequestBody @Valid BoardWriteRequestDTO requestDTO) {
        return responseService.getSingleResult(boardService.insertBoard(requestDTO));
    }

    // 게시글 전체 조회 (잠금이 안된 게시물만 조회 가능)
    @GetMapping()
    public MultipleResult<BoardGetBoardListResponseDTO> findAllBoardList() {
        return responseService.getMultipleResult(boardService.findAllBoardList());
    }

    // 내가 작성한 게시글 전체 조회 (잠금한 게시물 볼 수 있음)
    @GetMapping("/my-board-list/email/{email}")
    public MultipleResult<BoardGetBoardListResponseDTO> findMyBoardList(@PathVariable("email") String email) {
        return responseService.getMultipleResult(boardService.findAllMyBoardList(email));
    }

    // 게시글 상세 조회 (잠금글은 본인만 조회 가능)
    @GetMapping("/board-detail/boardUUID/{boardUUID}")
    public SingleResult<BoardGetBoardResponseDTO> findBoard(@PathVariable("boardUUID") UUID boardUUID) {
        return responseService.getSingleResult(boardService.findBoard(boardUUID));
    }

    // 게시글 수정
    @PutMapping ("/{boardUUID}")
    public SingleResult<BoardUpdateResponseDTO> updateBoard(@PathVariable("boardUUID") UUID boardUUID,
                                                            @RequestBody @Valid BoardUpdateRequestDTO requestDTO) {
        return responseService.getSingleResult(boardService.updateBoard(boardUUID, requestDTO));
    }

    // 게시글 삭제
    @DeleteMapping("/{boardUUID}")
    public SingleResult<BoardDeleteResponseDTO> deleteBoard(@PathVariable("boardUUID") UUID boardUUID) {
        return responseService.getSingleResult(boardService.deleteBoard(boardUUID));
    }
}
