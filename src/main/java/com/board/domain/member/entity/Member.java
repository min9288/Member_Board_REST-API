package com.board.domain.member.entity;

import com.board.domain.board.entity.Board;
import com.board.domain.member.entity.enumPackage.Role;
import com.board.domain.shopping.order.entity.Order;
import com.board.domain.shopping.product.entity.Product;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "members")
public class Member {

    // 회원 PK
    @Id @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)", name = "member_uuid")
    private UUID memberUUID;

    // 회원 아이디
    @Column(nullable = false, unique = true, length = 100)
    private String email;

    // 회원 패스워드
    @Column(nullable = false, length = 100)
    private String password;

    // 닉네임
    @Column(nullable = false, length = 40)
    private String nickname;

    // 적립 포인트
    @ColumnDefault("0")
    private int money;

    // 적립 포인트
    @ColumnDefault("0")
    private int point;

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

    // 재발행 토큰
    @Column(name = "refresh_token")
    private String refreshToken;

    // 이메일 인증값
    @Column(name = "email_auth")
    private  Boolean emailAuth;

    // 조인컬럼 (Baord)
    @OneToMany(mappedBy = "writer", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Board> boardList = new ArrayList<>();

    @ElementCollection(fetch = FetchType.LAZY)
    @Enumerated(EnumType.STRING)
    private List<Role> roles = new ArrayList<>();

    @Builder
    public Member(String email, String password, String nickname, List<Role> roles, Boolean emailAuth, String provider) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.roles = Collections.singletonList(Role.ROLE_MEMBER);
        this.emailAuth = emailAuth;
    }

    @Builder
    public Member(String nickname, int money) {
        this.nickname = nickname;
        this.money = money;
    }

    // 게시글 연관관계 메서드
    public void addBoard(Board board) {
        boardList.add(board);
    }

    public void addRole(Role role) {
        this.roles.add(role);
    }

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public void emailVerifiedCheck() {
        this.emailAuth = true;
    }

    public Member update(Member member) {
        this.nickname = member.nickname;
        this.money = member.money;
        return this;
    }

}
