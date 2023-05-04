package com.meta.junggushop.member.controller;

import com.meta.junggushop.common.dto.ResponseDto;
import com.meta.junggushop.common.message.ResultCode;
import com.meta.junggushop.member.dto.SignupRequestDto;
import com.meta.junggushop.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.meta.junggushop.common.message.ResultCode.MEMBER_SIGNUP_SUCCESS;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<ResponseDto<ResultCode>> signup(@RequestBody @Valid SignupRequestDto signupRequestDto){
        memberService.signup(signupRequestDto);
        return ResponseEntity.status(201)
                .body(new ResponseDto<>(MEMBER_SIGNUP_SUCCESS,null));
    }
}
