package com.board.domain.board.service;

import com.board.domain.board.dto.requestDTO.BoardUpdateRequestDTO;
import com.board.domain.board.dto.requestDTO.BoardWriteRequestDTO;
import com.board.domain.board.dto.responseDTO.*;
import com.board.domain.board.entity.Board;
import com.board.domain.board.entity.enumPackage.BoardStatus;
import com.board.domain.board.repository.BoardCustomRepositoryImpl;
import com.board.domain.board.repository.BoardRepository;
import com.board.domain.member.entity.Member;
import com.board.domain.member.repository.MemberRepository;
import com.board.exception.*;
import com.board.security.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;
    private final BoardCustomRepositoryImpl boardCustomRepositoryImpl;
    private final MemberRepository memberRepository;

    // 게시글 작성
    @Override
    @Transactional
    public BoardWriteResponseDTO insertBoard(BoardWriteRequestDTO requestDTO) throws ProcessFailureException {
        Board board = boardRepository.save(
                Board.builder()
                        .title(requestDTO.getTitle())
                        .contents(requestDTO.getContents())
                        .boardStatus(requestDTO.getBoardStatus())
                        .build());
        board.confirmWriter(findMember());
        return BoardWriteResponseDTO.builder()
                .boardUUID(board.getBoardUUID())
                .title(board.getTitle())
                .contents(board.getContents())
                .writer(board.getWriter().getNickname())
                .boardStatus(board.getBoardStatus())
                .build();
    }

    // 게시글 수정
    @Override
    @Transactional
    public BoardUpdateResponseDTO updateBoard(UUID boardUUID, BoardUpdateRequestDTO requestDTO) {
        Member member = findMember();
        Board board = findBoardByBoardUUID(boardUUID);
        // 수정하려는 게시글 작성자와 로그인한 회원과 동일한지 확인
        if(!board.getWriter().getMemberUUID().equals(member.getMemberUUID()))
            throw new MemberNotWriterException();

        board.setTitle(requestDTO.getTitle());
        board.setContents(requestDTO.getContents());
        board.setBoardStatus(requestDTO.getBoardStatus());
        boardRepository.save(board);

        return BoardUpdateResponseDTO.builder()
                .boardUUID(board.getBoardUUID())
                .title(board.getTitle())
                .contents(board.getContents())
                .writer(board.getWriter().getNickname())
                .boardStatus(board.getBoardStatus())
                .updateDate(board.getUpdateDate())
                .build();
    }


    // 게시글 전체보기
    @Override
    public List<BoardGetBoardListResponseDTO> findAllBoardList() {
        List<Board> boards = boardCustomRepositoryImpl.findAllBoard();
        return boards.stream()
                .map(board -> BoardGetBoardListResponseDTO.createDTO(board))
                .collect(Collectors.toList());
    }

    // 내가 작성한 게시글 전체 조회
    @Override
    public List<BoardGetBoardListResponseDTO> findAllMyBoardList(String email) {
        Member member = findMember();

        // 조회할려고 하는 회원 게시글리스트의 작성자와 현재 로그인한 사람과 동일한지 검사
        if(!member.getEmail().equals(email))
            throw new MemberNotWriterException();

        List<Board> boards = boardCustomRepositoryImpl.findAllMyBoard(email);
        return boards.stream()
                .map(board -> BoardGetBoardListResponseDTO.createDTO(board))
                .collect(Collectors.toList());
    }

    // 게시글 상세보기
    @Override
    public BoardGetBoardResponseDTO findBoard(UUID boardUUID) {
        Board board = findBoardByBoardUUID(boardUUID);
        // 게시글 잠금상태가 private 라면, 로그인한 사람과 조회할려는 게시글 작성자와 동일한지 검사
        if(board.getBoardStatus() == BoardStatus.PRIVATE_BOARD) {
            Member member = findMember();
            if (!board.getWriter().getMemberUUID().equals(member.getMemberUUID()))
                throw new MemberNotWriterException();
        }

        // 조회수 1 증가
        board.setHit(board.getHit() + 1);
        boardRepository.save(board);

        return BoardGetBoardResponseDTO.builder()
                .boardUUID(board.getBoardUUID())
                .title(board.getTitle())
                .contents(board.getContents())
                .writer(board.getWriter().getNickname())
                .hit(board.getHit())
                .enrollDate(board.getEnrollDate())
                .boardStatus(board.getBoardStatus())
                .build();
    }

    // 게시글 삭제
    @Override
    @Transactional
    public BoardDeleteResponseDTO deleteBoard(UUID boardUUID) {
        Member member = findMember();
        Board board = findBoardByBoardUUID(boardUUID);

        // 삭제하려는 게시글 작성자와 로그인한 회원과 동일한지 확인
        if(!board.getWriter().getMemberUUID().equals(member.getMemberUUID()))
            throw new MemberNotWriterException();

        if (boardRepository.deleteBoardByBoardUUID(boardUUID) != 1)
            throw new BoardDeleteFailureException();

        return BoardDeleteResponseDTO.builder()
                .successMSG("삭제 성공")
                .build();
    }

    // 현재 로그인한 멤버 정보 조회
    public Member findMember() {
        return memberRepository.findByEmail(SecurityUtil.getLoginUsername()).orElseThrow(MemberNotFoundException::new);
    }

    // 게시글 UUID로 게시글 정보 조회
    public Board findBoardByBoardUUID(UUID boardUUID) {
        return boardRepository.findBoardByBoardUUID(boardUUID).orElseThrow(BoardNotFoundException::new);
    }

}
