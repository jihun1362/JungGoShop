package com.meta.junggushop.member.service;

import com.meta.junggushop.common.exception.CustomException;
import com.meta.junggushop.member.dto.SignupRequestDto;
import com.meta.junggushop.member.entity.Member;
import com.meta.junggushop.member.mapper.MemberMapper;
import com.meta.junggushop.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.meta.junggushop.common.message.ErrorCode.DUPLICATE_EMAIL_ERROE;
import static com.meta.junggushop.common.message.ErrorCode.DUPLICATE_NICKNAME_ERROE;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;

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
}
