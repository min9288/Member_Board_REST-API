package com.board.domain.board.repository;

import com.board.domain.board.entity.Board;
import org.springframework.data.domain.Page;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BoardCustomRepository {
    List<Board> findAllBoard();

    List<Board> findAllMyBoard(String email);

    @Transactional
    Long deleteBoardByBoardUUID(UUID boardUUID);

}
