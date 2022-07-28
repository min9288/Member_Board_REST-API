package com.board.domain.board.service;

import com.board.domain.board.dto.requestDTO.BoardUpdateRequestDTO;
import com.board.domain.board.dto.requestDTO.BoardWriteRequestDTO;
import com.board.domain.board.dto.responseDTO.BoardGetBoardListResponseDTO;
import com.board.domain.board.dto.responseDTO.BoardGetBoardResponseDTO;
import com.board.domain.board.dto.responseDTO.BoardUpdateResponseDTO;
import com.board.domain.board.dto.responseDTO.BoardWriteResponseDTO;
import com.board.domain.board.entity.Board;
import com.board.domain.board.repository.BoardCustomRepositoryImpl;
import com.board.domain.board.repository.BoardRepository;
import com.board.domain.member.entity.Member;
import com.board.domain.member.repository.MemberRepository;
import com.board.domain.result.MultipleResult;
import com.board.domain.result.SingleResult;
import com.board.exception.MemberNotFoundException;
import com.board.exception.MemberNotWriterException;
import com.board.exception.ProcessFailureException;
import com.board.security.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
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
                .title(board.getTitle())
                .contents(board.getContents())
                .writer(board.getWriter())
                .boardStatus(board.getBoardStatus())
                .enrollDate(board.getEnrollDate())
                .build();
    }

    @Override
    public BoardUpdateResponseDTO updateBoard(BoardUpdateRequestDTO requestDTO) {
        return null;
    }

    @Override
    public List<BoardGetBoardListResponseDTO> findAllBoardList() {
        List<Board> boards = boardCustomRepositoryImpl.findAllBoard();
        return boards.stream()
                .map(board -> BoardGetBoardListResponseDTO.createDTO(board))
                .collect(Collectors.toList());
    }

    @Override
    public List<BoardGetBoardListResponseDTO> findAllMyBoardList(String email) {
        Member member = findMember();
        if(!(member.getEmail().equals(email)))
            throw new MemberNotWriterException();
        List<Board> boards = boardCustomRepositoryImpl.findAllMyBoard(email);
        return boards.stream()
                .map(board -> BoardGetBoardListResponseDTO.createDTO(board))
                .collect(Collectors.toList());
    }

    @Override
    public BoardGetBoardResponseDTO findBoard(UUID boardUUID) {
        return null;
    }

    public Member findMember() {
        return memberRepository.findByEmail(SecurityUtil.getLoginUsername()).orElseThrow(MemberNotFoundException::new);
    }
}
