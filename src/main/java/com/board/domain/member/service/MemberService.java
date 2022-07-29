package com.board.domain.member.service;

import com.board.domain.member.dto.responseDTO.MemberGetInfoResponseDTO;

import java.util.UUID;

public interface MemberService {

    MemberGetInfoResponseDTO getMemberInfo(UUID memberUUID);
}
