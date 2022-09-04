package com.board.domain.member.dto.responseDTO;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
public class MemberUpdateResponseDTO {

    private UUID memberUUID;
    private String email;
    private String nickname;
    private int money;
    private int point;
    private LocalDate enrollDate;
    private LocalDate updateDate;

    @Builder
    public MemberUpdateResponseDTO(UUID memberUUID, String email, String nickname, int money, int point, LocalDate enrollDate, LocalDate updateDate) {
        this.memberUUID = memberUUID;
        this.email = email;
        this.nickname = nickname;
        this.money = money;
        this.point = point;
        this.enrollDate = enrollDate;
        this.updateDate = updateDate;
    }
}
