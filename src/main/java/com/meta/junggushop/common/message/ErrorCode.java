package com.meta.junggushop.common.message;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    //회원가입, 로그인 에러
    INVALID_EMAIL_PATTERN("email 형식에 맞지 않습니다.",400),
    INVALID_PASSWORD_PATTERN("비밀번호는 영문, 숫자, 특수문자(!@#$%^&+=)는 한 문자씩 포함 최소 8자리 이상입니다.",400),

    //필터부분 에러
    FORBIDDEN_ERROR("서버 사용 권한이 없습니다.",403),
    TOKEN_ERROR("토큰이 유효하지 않습니다.",401),
    USER_NOT_FOUND("존재하지 않는 유저 입니다.",404),

    //서버 에러
    INTERNAL_SERVER_ERROR("서버 에러입니다. 서버 팀에 연락주세요!", 500),

    ;
    private final String msg;
    private final int statusCode;
}
