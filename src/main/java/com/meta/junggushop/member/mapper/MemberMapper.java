package com.meta.junggushop.member.mapper;

import com.meta.junggushop.member.dto.SignupRequestDto;
import com.meta.junggushop.member.entity.Member;
import com.meta.junggushop.member.entity.UserRoleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberMapper {
    /**
     * - Dto → Entity, Entity → Dto 변환의 책임을 Mapper 클래스가 가진다.
     *     1. 가시성 업 ↑
     *     2. 관리를 Mapper 에서만 하면 되니 유지보수성 업 ↑
     *     3. DTO와 Entity간의 결합도 줄이기!
     */
    private final PasswordEncoder passwordEncoder;
    public Member toMember(SignupRequestDto signupRequestDto){
        return Member.builder()
                .email(signupRequestDto.getEmail())
                .password(passwordEncoder.encode(signupRequestDto.getPassword()))
                .nickname(signupRequestDto.getNickname())
                .address(signupRequestDto.getAddress())
                .role(UserRoleEnum.USER)
                .build();
    }
}
