package com.board.domain.member.service;

import com.board.domain.email.dto.requestDTO.EmailAuthRequestDto;
import com.board.domain.email.entity.EmailAuth;
import com.board.domain.email.repository.EmailAuthRepository;
import com.board.domain.email.service.EmailService;
import com.board.domain.member.dto.requestDTO.MemberLoginRequestDto;
import com.board.domain.member.dto.requestDTO.MemberRegisterRequestDto;
import com.board.domain.member.dto.requestDTO.TokenRequestDto;
import com.board.domain.member.dto.responseDTO.MemberGetInfoResponseDTO;
import com.board.domain.member.dto.responseDTO.MemberLoginResponseDto;
import com.board.domain.member.dto.responseDTO.MemberRegisterResponseDto;
import com.board.domain.member.dto.responseDTO.TokenResponseDto;
import com.board.domain.member.entity.Member;
import com.board.domain.member.repository.MemberRepository;
import com.board.exception.*;
import com.board.security.jwt.JwtTokenProvider;
import com.board.security.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SignService {

    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    private final MemberRepository memberRepository;
    private final EmailAuthRepository emailAuthRepository;

    private final EmailService emailService;


    // DTO로 들어온 값을 통해 회원가입을 진행
    @Transactional
    public MemberRegisterResponseDto registerMember(MemberRegisterRequestDto requestDto) {
        validateDuplicated(requestDto.getEmail());
        EmailAuth emailAuth = emailAuthRepository.save(
                EmailAuth.builder()
                        .email(requestDto.getEmail())
                        .authToken(UUID.randomUUID().toString())
                        .expired(false)
                        .build()
        );

        Member member = memberRepository.save(
                Member.builder()
                        .email(requestDto.getEmail())
                        .password(passwordEncoder.encode(requestDto.getPassword()))
                        .nickname(requestDto.getNickname())
                        .emailAuth(false)
                        .build()
        );
        emailService.send(emailAuth.getEmail(), emailAuth.getAuthToken());
        return MemberRegisterResponseDto.builder()
                .memberUUID(member.getMemberUUID())
                .email(member.getEmail())
                .authToken(emailAuth.getAuthToken())
                .build();
    }

    // 중복값 검사
    public void validateDuplicated(String email) {
        if (memberRepository.findByEmail(email).isPresent())
            throw new MemberEmailAlreadyExistsException();
    }

    // 이메일 인증
    @Transactional
    public void confirmEmail(EmailAuthRequestDto requestDto) {
        EmailAuth emailAuth = emailAuthRepository.findValidAuthByEmail(requestDto.getEmail(), requestDto.getAuthToken(), LocalDateTime.now())
                .orElseThrow(EmailAuthTokenNotFountException::new);
        Member member = memberRepository.findByEmail(requestDto.getEmail()).orElseThrow(MemberNotFoundException::new);
        emailAuth.useToken();
        member.emailVerifiedCheck();
    }

    // 로그인
    @Transactional
    public MemberLoginResponseDto loginMember(MemberLoginRequestDto requestDto) {
        Member member = memberRepository.findByEmail(requestDto.getEmail()).orElseThrow(MemberNotFoundException::new);
        if (!passwordEncoder.matches(requestDto.getPassword(), member.getPassword()))
            throw new LoginFailureException();
        if (!member.getEmailAuth())
            throw new EmailNotAuthenticatedException();
        member.updateRefreshToken(jwtTokenProvider.createRefreshToken());
        return new MemberLoginResponseDto(member.getMemberUUID(), member.getNickname(), jwtTokenProvider.createToken(requestDto.getEmail()), member.getRefreshToken());
    }

    // 토큰 재발행
    @Transactional
    public TokenResponseDto reIssue(TokenRequestDto requestDto) {
        if (!jwtTokenProvider.validateTokenExpiration(requestDto.getRefreshToken()))
            throw new InvalidRefreshTokenException();

        Member member = findMemberByToken(requestDto);

        if (!member.getRefreshToken().equals(requestDto.getRefreshToken()))
            throw new InvalidRefreshTokenException();

        String accessToken = jwtTokenProvider.createToken(member.getEmail());
        String refreshToken = jwtTokenProvider.createRefreshToken();

        member.updateRefreshToken(refreshToken);
        return new TokenResponseDto(accessToken, refreshToken);

    }


    public Member findMemberByToken(TokenRequestDto requestDto) {
        Authentication auth = jwtTokenProvider.getAuthentication(requestDto.getAccessToken());
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        String username = userDetails.getUsername();
        return memberRepository.findByEmail(username).orElseThrow(MemberNotFoundException::new);
    }


}
