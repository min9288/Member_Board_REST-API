package com.board.domain.member.service;

import com.board.domain.member.dto.responseDTO.MemberGetInfoResponseDTO;
import com.board.domain.member.entity.Member;
import com.board.domain.member.repository.MemberRepository;
import com.board.exception.MemberDoNotUseOtherThingException;
import com.board.exception.MemberNotFoundException;
import com.board.security.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    // 내 정보 조회
    @Override
    public MemberGetInfoResponseDTO getMemberInfo(UUID memberUUID) {
        Member member = findMember();

        if(!member.getMemberUUID().equals(memberUUID))
            throw new MemberDoNotUseOtherThingException();

        return MemberGetInfoResponseDTO.builder()
                .memberUUID(member.getMemberUUID())
                .email(member.getEmail())
                .nickname(member.getNickname())
                .enrollDate(member.getEnrollDate())
                .build();
    }

    // 현재 로그인한 멤버 정보 조회
    public Member findMember() {
        return memberRepository.findByEmail(SecurityUtil.getLoginUsername()).orElseThrow(MemberNotFoundException::new);
    }
}
