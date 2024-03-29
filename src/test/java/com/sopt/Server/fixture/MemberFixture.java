package com.sopt.Server.fixture;

import com.sopt.Server.domain.Member;

public class MemberFixture {

    public static Member createMemberSong(){
        return Member.builder()
                .name("송민규")
                .realAge(20)
                .build();
    }

    public static Member createMember(String name, int age){
        return Member.builder()
                .name(name)
                .realAge(age)
                .build();
    }
}
