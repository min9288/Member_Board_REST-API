package com.board.domain.member.dto.responseDTO;

import com.board.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
public class MemberGetInfoResponseDTO {
    private UUID memberUUID;
    private String email;
    private String nickname;
    private int money;
    private int point;
    private LocalDate enrollDate;

    @Builder
    public MemberGetInfoResponseDTO(UUID memberUUID, String email, String nickname, int money, int point, LocalDate enrollDate) {
        this.memberUUID = memberUUID;
        this.email = email;
        this.nickname = nickname;
        this.money = money;
        this.point = point;
        this.enrollDate = enrollDate;
    }

}
