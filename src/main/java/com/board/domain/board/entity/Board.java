package com.board.domain.board.entity;

import com.board.domain.board.entity.enumPackage.BoardStatus;
import com.board.domain.member.entity.Member;
import com.fasterxml.jackson.annotation.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "boards")
public class Board {

    // 게시글 PK
    @Id @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)", name = "board_uuid")
    private UUID boardUUID;

    // 게시글 제목
    @Column(nullable = false, length = 100)
    private String title;

    // 게시글 내용
    @Column(nullable = false, length = 1000)
    private String contents;

    // 게시글 조회수
    @ColumnDefault("0")
    private int hit;

    // 작성자
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "member_uuid")
    private Member writer;

    // 잠금 유무
    @Enumerated(EnumType.STRING)
    @Column(name = "board_status")
    private BoardStatus boardStatus;

    // 작성일
    @CreationTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    @Column(nullable = false, updatable = false, name = "enroll_date")
    private LocalDate enrollDate;

    // 수정일
    @UpdateTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    @Column(name = "update_date")
    private LocalDate updateDate;


    // 회원 연관관계 메서드
    public void confirmWriter(Member writer) {
        this.writer = writer;
        writer.addBoard(this);
    }
    
    @Builder
    public Board(String title, String contents, BoardStatus boardStatus) {
        this.title = title;
        this.contents = contents;
        this.boardStatus = boardStatus;
    }

    public Board update(Board board) {
        this.title = board.title;
        this.contents = board.contents;
        this.boardStatus = board.boardStatus;
        return this;
    }

}
