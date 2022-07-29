package com.board.domain.board.repository;

import com.board.domain.board.entity.Board;
import com.board.domain.board.entity.QBoard;
import com.board.domain.board.entity.enumPackage.BoardStatus;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.board.domain.board.entity.QBoard.board;
import static com.board.domain.member.entity.QMember.member;


public class BoardCustomRepositoryImpl implements BoardCustomRepository{

    JPAQueryFactory jpaQueryFactory;

    public BoardCustomRepositoryImpl(EntityManager em) {
        jpaQueryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Board> findAllBoard() {
        List<Board> boardList = jpaQueryFactory.selectFrom(board)
                .where(board.boardStatus.eq(BoardStatus.PUBLIC_BOARD))
                .leftJoin(board.writer, member)
                .fetch();
        return boardList;
    }

    @Override
    public List<Board> findAllMyBoard(String email) {
        List<Board> boardList = jpaQueryFactory.selectFrom(board)
                .where(board.writer.email.eq(email))
                .leftJoin(board.writer, member)
                .fetch();
        return boardList;
    }

    @Override
    public Long deleteBoardByBoardUUID(UUID boardUUID) {
        Long delete = jpaQueryFactory.delete(board)
                .where(board.boardUUID.eq(boardUUID))
                .execute();
        return delete;
    }


}
