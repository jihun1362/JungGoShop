package com.meta.junggushop.member.service;

import com.meta.junggushop.common.exception.CustomException;
import com.meta.junggushop.member.dto.LoginRequestDto;
import com.meta.junggushop.member.dto.SignupRequestDto;
import com.meta.junggushop.member.entity.Member;
import com.meta.junggushop.member.mapper.MemberMapper;
import com.meta.junggushop.member.repository.MemberRepository;
import com.meta.junggushop.security.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

import static com.meta.junggushop.common.message.ErrorCode.DUPLICATE_EMAIL_ERROE;
import static com.meta.junggushop.common.message.ErrorCode.DUPLICATE_NICKNAME_ERROE;
import static com.meta.junggushop.common.message.ErrorCode.INCORRECT_PASSWORD_ERROR;
import static com.meta.junggushop.common.message.ErrorCode.MEMBER_NOT_FOUND_ERROR;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void signup(SignupRequestDto signupRequestDto) {
        Member member = memberMapper.toMember(signupRequestDto);

        //이메일 중복 체크
        Optional<Member> emailDuplicateCheck = memberRepository.findByEmail(member.getEmail());
        if (emailDuplicateCheck.isPresent()) throw new CustomException(DUPLICATE_EMAIL_ERROE);

        // 닉네임 중복 확인
        Optional<Member> nicknameDuplicateCheck = memberRepository.findByNickname(member.getNickname());
        if (nicknameDuplicateCheck.isPresent()) throw new CustomException(DUPLICATE_NICKNAME_ERROE);

        memberRepository.save(member);
    }

    @Transactional
    public void login(LoginRequestDto loginRequestDto, HttpServletResponse response) {
        //멤버가 존재하는지 확인
        Member member = memberRepository.findByEmail(loginRequestDto.getEmail()).orElseThrow(
                () -> new CustomException(MEMBER_NOT_FOUND_ERROR)
        );

        //비밀번호 일치 확인
        if (!passwordEncoder.matches(loginRequestDto.getPassword(), member.getPassword())) {
            throw new CustomException(INCORRECT_PASSWORD_ERROR);
        }

        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(member.getEmail(), member.getRole()));
    }

    @Transactional(readOnly = true)
    public void checkEmail(String email) {
        //이메일 중복 체크
        Optional<Member> emailDuplicateCheck = memberRepository.findByEmail(email);
        if (emailDuplicateCheck.isPresent()) throw new CustomException(DUPLICATE_EMAIL_ERROE);
    }
}
