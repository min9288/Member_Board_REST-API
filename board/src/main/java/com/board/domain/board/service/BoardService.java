package com.board.domain.board.service;


import com.board.domain.board.dto.requestDTO.BoardUpdateRequestDTO;
import com.board.domain.board.dto.requestDTO.BoardWriteRequestDTO;
import com.board.domain.board.dto.responseDTO.BoardGetBoardListResponseDTO;
import com.board.domain.board.dto.responseDTO.BoardGetBoardResponseDTO;
import com.board.domain.board.dto.responseDTO.BoardUpdateResponseDTO;
import com.board.domain.board.dto.responseDTO.BoardWriteResponseDTO;
import com.board.domain.result.MultipleResult;
import com.board.domain.result.SingleResult;

import java.util.List;
import java.util.UUID;

public interface BoardService {

    // 게시글 등록
    BoardWriteResponseDTO insertBoard(BoardWriteRequestDTO requestDTO);

    // 게시글 삭제
    BoardUpdateResponseDTO updateBoard(UUID boardUUID, BoardUpdateRequestDTO requestDTO);

    // 게시글 전체 조회
    List<BoardGetBoardListResponseDTO> findAllBoardList();

    // 내 게시글 전체 조회
    List<BoardGetBoardListResponseDTO> findAllMyBoardList(String email);

    // 게시글 상세 조회
    BoardGetBoardResponseDTO findBoard(UUID boardUUID);
}
