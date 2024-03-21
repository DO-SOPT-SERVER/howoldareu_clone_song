package com.sopt.Server.controller.response;


import com.sopt.Server.domain.Member;

public record MemberGetResponse(Long memberId, String nickName, int realAge) {
    public static MemberGetResponse of(Member member) {
        return new MemberGetResponse(member.getId(), member.getName(), member.getRealAge());
    }
}
