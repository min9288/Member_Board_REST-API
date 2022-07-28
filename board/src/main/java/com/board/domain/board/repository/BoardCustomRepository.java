package com.board.domain.board.repository;

import com.board.domain.board.entity.Board;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface BoardCustomRepository {
    List<Board> findAllBoard();

    List<Board> findAllMyBoard(String email);

}
