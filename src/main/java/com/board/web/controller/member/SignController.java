package com.board.web.controller.member;

import com.board.domain.email.dto.requestDTO.EmailAuthRequestDTO;
import com.board.domain.member.dto.requestDTO.MemberLoginRequestDTO;
import com.board.domain.member.dto.requestDTO.MemberRegisterRequestDTO;
import com.board.domain.member.dto.requestDTO.TokenRequestDTO;
import com.board.domain.member.dto.responseDTO.MemberLoginResponseDTO;
import com.board.domain.member.dto.responseDTO.MemberRegisterResponseDTO;
import com.board.domain.member.dto.responseDTO.TokenResponseDTO;
import com.board.domain.member.service.SignService;
import com.board.domain.response.service.ResponseService;
import com.board.domain.result.SingleResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/signs")
public class SignController {

    private final SignService signService;
    private final ResponseService responseService;

    // 회원가입
    @PostMapping("/register")
    public SingleResult<MemberRegisterResponseDTO> register(@RequestBody @Valid MemberRegisterRequestDTO requestDto) {
        MemberRegisterResponseDTO responseDto = signService.registerMember(requestDto);
        return responseService.getSingleResult(responseDto);
    }

    // 이메일 인증
    @GetMapping("/confirm-email")
    public SingleResult<String> confirmEmail(@ModelAttribute EmailAuthRequestDTO requestDto) {
        signService.confirmEmail(requestDto);
        return responseService.getSingleResult("인증이 완료되었습니다.");
    }

    // 로그인
    @PostMapping("/login")
    public SingleResult<MemberLoginResponseDTO> login(@RequestBody @Valid MemberLoginRequestDTO requestDto) {
        MemberLoginResponseDTO responseDto = signService.loginMember(requestDto);
        return responseService.getSingleResult(responseDto);
    }

    // 토큰 재발행
    @PostMapping("/reissue")
    public SingleResult<TokenResponseDTO> reIssue(@RequestBody @Valid TokenRequestDTO tokenRequestDto) {
        TokenResponseDTO responseDto = signService.reIssue(tokenRequestDto);
        return responseService.getSingleResult(responseDto);
    }


}
