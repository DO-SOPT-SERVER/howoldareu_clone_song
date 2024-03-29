package com.sopt.Server.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

import com.sopt.Server.common.ApiResponse;
import com.sopt.Server.controller.response.MemberGetResponse;
import com.sopt.Server.domain.Answer;
import com.sopt.Server.domain.Member;
import com.sopt.Server.domain.Question;
import com.sopt.Server.domain.Result;
import com.sopt.Server.repository.MemberJpaRepository;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

@ExtendWith(MockitoExtension.class)
public class MemberServiceTest {
    @InjectMocks
    private MemberService memberService;

    @Mock
    private MemberJpaRepository memberJpaRepository;

    private Member member;

    @BeforeEach
    void init() {
        member = new Member("송민규", 20);
    }

    @Test
    void 멤버저장_멤버조회성공_이미멤버인경우(){
        // given
        doReturn(Optional.of(member)).when(memberJpaRepository).findByName(any());

        // when
        ApiResponse<MemberGetResponse> response = memberService.saveMember(member.getName(),
                member.getRealAge());

        // then
        Assertions.assertThat(response.getCode()).isEqualTo(HttpStatus.OK.value());
        Assertions.assertThat(response.getData().nickName()).isEqualTo(member.getName());
        Assertions.assertThat(response.getData().realAge()).isEqualTo(member.getRealAge());

    }

    @Test
    void 멤버저장_멤버저장성공_멤버로저장되지않은경우(){
        // given
        doReturn(Optional.ofNullable(null)).when(memberJpaRepository).findByName(any());
        doReturn(member).when(memberJpaRepository).save(any());

        // when
        ApiResponse<MemberGetResponse> response = memberService.saveMember(member.getName(),
                member.getRealAge());

        // then
        Assertions.assertThat(response.getCode()).isEqualTo(HttpStatus.CREATED.value());
        Assertions.assertThat(response.getData().nickName()).isEqualTo(member.getName());
        Assertions.assertThat(response.getData().realAge()).isEqualTo(member.getRealAge());
    }
}
