package com.board.exception.advise;

import com.board.domain.response.service.ResponseService;
import com.board.domain.result.Result;
import com.board.exception.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class ExceptionAdvice {

    private final ResponseService responseService;

    @ExceptionHandler(MemberEmailAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result userEmailAlreadyExistsException() {
        return responseService.getFailureResult(-400, "이미 존재하는 이메일입니다.");
    }

    @ExceptionHandler(LoginFailureException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result loginFailureException() {
        return responseService.getFailureResult(-400, "아이디 혹은 비밀번호가 틀립니다.");
    }

    @ExceptionHandler(MemberNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result memberNotFoundException() {
        return responseService.getFailureResult(-404, "회원정보를 찾을 수 없습니다.");
    }

    @ExceptionHandler(EmailAuthTokenNotFountException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result emailAuthTokenNotFountException() {
        return responseService.getFailureResult(-401, "유효하지 않은 인증요청입니다.");
    }

    @ExceptionHandler(EmailNotAuthenticatedException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result emailAuthenticationException() {
        return responseService.getFailureResult(-401, "이메일 인증이 필요합니다.");
    }

    @ExceptionHandler(InvalidRefreshTokenException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result invalidRefreshTokenException() {
        return responseService.getFailureResult(-401, "Refresh Token이 유효하지 않습니다.");
    }

    @ExceptionHandler(AuthenticationEntryPointException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result authenticationEntryPointException() {
        return responseService.getFailureResult(-401, "인증 실패에 따른 예외가 발생했습니다.");
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result accessDeniedException() {
        return responseService.getFailureResult(-401, "인가에 따른 예외가 발생했습니다.");
    }

    @ExceptionHandler(ProcessFailureException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result processFailureException() {
        return responseService.getFailureResult(-400, "처리 과정에 오류가 발생했습니다.");
    }

    @ExceptionHandler(MemberNotWriterException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result memberNotWriterException() {
        return responseService.getFailureResult(-401, "게시글의 작성자가 아닙니다.");
    }

    @ExceptionHandler(BoardNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result boardNotFoundException() {
        return responseService.getFailureResult(-404, "게시글이 존재하지 않습니다.");
    }

    @ExceptionHandler(BoardDeleteFailureException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result boardDeleteFailureException() {
        return responseService.getFailureResult(-400, "삭제에 실패 했습니다.");
    }

    @ExceptionHandler(MemberDoNotUseOtherThingException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result moardDeleteFailureException() {
        return responseService.getFailureResult(-401, "다른 회원의 정보입니다, 본인의 정보만 조회 가능합니다.");
    }

    @ExceptionHandler(MemberNicknameAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result memberNicknameAlreadyExistsException() {
        return responseService.getFailureResult(-400, "이미 존재하는 닉네임 입니다.");
    }

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result productNotFoundException() {
        return responseService.getFailureResult(-404, "상품 정보를 찾을 수 없습니다.");
    }


}
