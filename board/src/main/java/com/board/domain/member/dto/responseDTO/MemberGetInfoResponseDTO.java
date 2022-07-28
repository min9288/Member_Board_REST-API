package com.board.domain.member.dto.responseDTO;

import com.board.domain.member.entity.Member;
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
    private LocalDate enrollDate;

    @Builder
    public MemberGetInfoResponseDTO(Member member) {
        this.memberUUID = member.getMemberUUID();
        this.email = member.getEmail();
        this.nickname = member.getNickname();
        this.enrollDate = member.getEnrollDate();
    }
}
