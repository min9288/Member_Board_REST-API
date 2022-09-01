package com.board.web.controller.member;

import com.board.domain.member.dto.responseDTO.MemberGetInfoResponseDTO;
import com.board.domain.member.service.MemberServiceImpl;
import com.board.domain.response.service.ResponseService;
import com.board.domain.result.SingleResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberServiceImpl memberServiceImpl;
    private final ResponseService responseService;

    // 내 정보 조회
    @GetMapping("/myInfo/{memberUUID}")
    public SingleResult<MemberGetInfoResponseDTO> getMemberInfo(@PathVariable("memberUUID") UUID memberUUID) {
        return responseService.getSingleResult(memberServiceImpl.getMemberInfo(memberUUID));
    }
}
