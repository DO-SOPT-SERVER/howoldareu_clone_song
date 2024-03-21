package com.sopt.Server.service;

import com.sopt.Server.common.ApiResponse;
import com.sopt.Server.controller.response.MemberGetResponse;
import com.sopt.Server.domain.Member;
import com.sopt.Server.exception.Success;
import com.sopt.Server.repository.MemberJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberJpaRepository memberJpaRepository;

    @Transactional
    public ApiResponse<MemberGetResponse> saveMember(String nickName, int age) {
        Member newMember = Member.builder().name(nickName).realAge(age).build();
        Member member = memberJpaRepository.findByName(nickName).orElse(null);//null이면
        if(member != null)//있다면
            return ApiResponse.success(Success.GET_MEMBER_SUCCESS, MemberGetResponse.of(member));
        else {
            memberJpaRepository.save(newMember);
        }
        return ApiResponse.success(Success.CREATE_MEMBER_SUCCESS, MemberGetResponse.of(newMember));
    }
}
