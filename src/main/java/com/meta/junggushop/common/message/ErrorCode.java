package com.meta.junggushop.common.message;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    //회원가입, 로그인 에러
    INVALID_EMAIL_PATTERN_ERROR("email 형식에 맞지 않습니다.", 400),
    INVALID_PASSWORD_PATTERN_ERROR("비밀번호는 영문, 숫자, 특수문자(!@#$%^&+=)는 한 문자씩 포함 최소 8자리 이상입니다.", 400),
    INVALID_NICKNAME_PATTERN("닉네임은 영문(소문자), 숫자, 한글(자음, 모음 단위 x) 조합에 2~10자입니다.",400),
    DUPLICATE_EMAIL_ERROE("중복된 이메일이 존재합니다.", 400),
    DUPLICATE_NICKNAME_ERROE("중복된 닉네임이 존재합니다.", 400),
    MEMBER_NOT_FOUND_ERROR("존재하지 않는 이메일이거나 이메일이 올바르지 않습니다.",404),
    INCORRECT_PASSWORD_ERROR("비밀번호가 일치하지 않습니다.",404),

    //멤버
    ALREADY_PERMISSION_SELLER_ERROR("이미 허가된 회원입니다.",400),

    //필터부분 에러
    FORBIDDEN_ERROR("서버 사용 권한이 없습니다.", 403),
    TOKEN_ERROR("토큰이 유효하지 않습니다.", 401),
    USER_NOT_FOUND_ERROR("존재하지 않는 유저 입니다.", 404),

    //서버 에러
    INTERNAL_SERVER_ERROR("서버 에러입니다. 서버 팀에 연락주세요!", 500),

    ;
    private final String msg;
    private final int statusCode;
}
