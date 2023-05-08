package com.meta.junggushop.member.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginRequestDtoTest {
    private String email;
    private String password;
}
