package com.meta.junggushop.member.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SignupRequestDtotest {
    private String email;
    private String password;
    private String nickname;
    private String address;
}
