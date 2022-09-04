package com.board.domain.member.service;

import com.board.domain.member.dto.requestDTO.MemberUpdateRequestDTO;
import com.board.domain.member.dto.responseDTO.MemberGetInfoResponseDTO;
import com.board.domain.member.dto.responseDTO.MemberUpdateResponseDTO;
import com.board.domain.member.entity.Member;
import com.board.domain.member.repository.MemberRepository;
import com.board.exception.MemberDoNotUseOtherThingException;
import com.board.exception.MemberNotFoundException;
import com.board.security.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    // 내 정보 조회
    @Override
    public MemberGetInfoResponseDTO getMemberInfo() {

        Member member = findMember();

        return MemberGetInfoResponseDTO.builder()
                .memberUUID(member.getMemberUUID())
                .email(member.getEmail())
                .nickname(member.getNickname())
                .money(member.getMoney())
                .point(member.getPoint())
                .enrollDate(member.getEnrollDate())
                .build();
    }

    // 내 정보 수정
    @Transactional
    @Override
    public MemberUpdateResponseDTO updateMember(MemberUpdateRequestDTO requestDTO) {

        Member member = findMember();
        member = member.update(requestDTO.toEntity());

        return MemberUpdateResponseDTO.builder()
                .memberUUID(member.getMemberUUID())
                .email(member.getEmail())
                .nickname(member.getNickname())
                .money(member.getMoney())
                .point(member.getPoint())
                .enrollDate(member.getEnrollDate())
                .updateDate(member.getUpdateDate())
                .build();
    }

    // 현재 로그인한 멤버 정보 조회
    public Member findMember() {
        return memberRepository.findByEmail(SecurityUtil.getLoginUsername()).orElseThrow(MemberNotFoundException::new);
    }
}
