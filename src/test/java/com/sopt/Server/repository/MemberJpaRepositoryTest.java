package com.sopt.Server.repository;

import com.sopt.Server.domain.Member;
import com.sopt.Server.fixture.MemberFixture;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class MemberJpaRepositoryTest {

    @Autowired
    private MemberJpaRepository memberJpaRepository;

    @Test
    void 멤버저장_성공_정상경우(){
        // given
        Member member = MemberFixture.createMemberSong();

        // when
        Member savedMember = memberJpaRepository.save(member);

        // then
        Assertions.assertThat(savedMember)
                .extracting("name", "realAge")
                .containsExactly("송민규", 20);
    }
}
