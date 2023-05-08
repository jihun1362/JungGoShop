package com.meta.junggushop.member.controller;

import com.meta.junggushop.common.dto.ResponseDto;
import com.meta.junggushop.common.message.ResultCode;
import com.meta.junggushop.member.dto.LoginRequestDto;
import com.meta.junggushop.member.dto.SignupRequestDto;
import com.meta.junggushop.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import static com.meta.junggushop.common.message.ResultCode.EMAIL_CHECK_SUCCESS;
import static com.meta.junggushop.common.message.ResultCode.MEMBER_LOGIN_SUCCESS;
import static com.meta.junggushop.common.message.ResultCode.MEMBER_SIGNUP_SUCCESS;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<ResponseDto<ResultCode>> signup(@RequestBody @Valid SignupRequestDto signupRequestDto) {
        memberService.signup(signupRequestDto);
        return ResponseEntity.status(201)
                .body(new ResponseDto<>(MEMBER_SIGNUP_SUCCESS, null));
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDto<ResultCode>> login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
        memberService.login(loginRequestDto, response);
        return ResponseEntity.status(200)
                .body(new ResponseDto<>(MEMBER_LOGIN_SUCCESS, null));
    }

    @GetMapping("/signup/check")
    public ResponseEntity<ResponseDto<ResultCode>> checkEmail(@RequestParam("email") String email) {
        memberService.checkEmail(email);
        return ResponseEntity.status(200)
                .body(new ResponseDto<>(EMAIL_CHECK_SUCCESS, null));
    }
}
