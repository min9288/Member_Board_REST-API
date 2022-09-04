package com.board.web.controller.member;

import com.board.domain.member.dto.requestDTO.MemberUpdateRequestDTO;
import com.board.domain.member.dto.responseDTO.MemberGetInfoResponseDTO;
import com.board.domain.member.dto.responseDTO.MemberUpdateResponseDTO;
import com.board.domain.member.service.MemberServiceImpl;
import com.board.domain.response.service.ResponseService;
import com.board.domain.result.SingleResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberServiceImpl memberServiceImpl;
    private final ResponseService responseService;

    // 내 정보 조회
    @GetMapping()
    public SingleResult<MemberGetInfoResponseDTO> getMemberInfo() {
        return responseService.getSingleResult(memberServiceImpl.getMemberInfo());
    }

    @PutMapping()
    public SingleResult<MemberUpdateResponseDTO> updateMember(@RequestBody @Valid MemberUpdateRequestDTO requestDTO) {
        return responseService.getSingleResult(memberServiceImpl.updateMember(requestDTO));
    }
}
