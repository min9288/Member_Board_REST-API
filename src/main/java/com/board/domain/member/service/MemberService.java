package com.board.domain.member.service;

import com.board.domain.member.dto.requestDTO.MemberUpdateRequestDTO;
import com.board.domain.member.dto.responseDTO.MemberGetInfoResponseDTO;
import com.board.domain.member.dto.responseDTO.MemberUpdateResponseDTO;

import java.util.UUID;

public interface MemberService {

    MemberGetInfoResponseDTO getMemberInfo();

    MemberUpdateResponseDTO updateMember(MemberUpdateRequestDTO requestDTO);

}
