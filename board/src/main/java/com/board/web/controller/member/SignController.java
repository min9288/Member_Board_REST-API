package com.board.web.controller.member;

import com.board.domain.email.dto.requestDTO.EmailAuthRequestDto;
import com.board.domain.member.dto.requestDTO.MemberLoginRequestDto;
import com.board.domain.member.dto.requestDTO.MemberRegisterRequestDto;
import com.board.domain.member.dto.requestDTO.TokenRequestDto;
import com.board.domain.member.dto.responseDTO.MemberLoginResponseDto;
import com.board.domain.member.dto.responseDTO.MemberRegisterResponseDto;
import com.board.domain.member.dto.responseDTO.TokenResponseDto;
import com.board.domain.member.service.SignService;
import com.board.domain.response.service.ResponseService;
import com.board.domain.result.SingleResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/sign")
public class SignController {

    private final SignService signService;
    private final ResponseService responseService;

    // 회원가입
    @PostMapping("/register")
    public SingleResult<MemberRegisterResponseDto> register(@RequestBody MemberRegisterRequestDto requestDto) {
        MemberRegisterResponseDto responseDto = signService.registerMember(requestDto);
        return responseService.getSingleResult(responseDto);
    }

    // 이메일 인증
    @GetMapping("/confirm-email")
    public SingleResult<String> confirmEmail(@ModelAttribute EmailAuthRequestDto requestDto) {
        signService.confirmEmail(requestDto);
        return responseService.getSingleResult("인증이 완료되었습니다.");
    }

    // 로그인
    @PostMapping("/login")
    public SingleResult<MemberLoginResponseDto> login(@RequestBody MemberLoginRequestDto requestDto) {
        MemberLoginResponseDto responseDto = signService.loginMember(requestDto);
        return responseService.getSingleResult(responseDto);
    }

    // 토큰 재발행
    @PostMapping("/reissue")
    public SingleResult<TokenResponseDto> reIssue(@RequestBody TokenRequestDto tokenRequestDto) {
        TokenResponseDto responseDto = signService.reIssue(tokenRequestDto);
        return responseService.getSingleResult(responseDto);
    }
}
