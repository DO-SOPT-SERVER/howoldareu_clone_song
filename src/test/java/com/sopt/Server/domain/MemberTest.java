package com.sopt.Server.domain;

import static com.sopt.Server.exception.Error.INVALID_AGE;
import static com.sopt.Server.exception.Error.INVALID_NAME;

import com.sopt.Server.exception.model.CustomException;
import com.sopt.Server.fixture.MemberFixture;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemberTest {

    @Test
    void 적정연령검증_성공_정상연령일경우(){
        Member member = new Member("송민규", 20);

        Assertions.assertThat(member.getRealAge()).isEqualTo(20);
    }

    @Test
    void 적정연령검증_예외발생_20세미만인경우(){
        // given, when, then

        Assertions.assertThatThrownBy(() -> new Member("송민규", 19))
                .isInstanceOf(CustomException.class)
                .hasMessageContaining(INVALID_AGE.getMessage());
    }

    @Test
    void 적정연령검증_예외발생_80세초과인경우(){
        // given, when, then

        Assertions.assertThatThrownBy(() -> new Member("송민규", 81))
                .isInstanceOf(CustomException.class)
                .hasMessageContaining(INVALID_AGE.getMessage());
    }

    @Test
    void 적정이름검증_성공_한글이름경우(){

        // when
        Member member = MemberFixture.createMemberSong();

        // then
        Assertions.assertThat(member.getName()).isEqualTo("송민규");
    }

    @Test
    void 적정이름검증_예외발생_영어이름경우(){

        // when
        Assertions.assertThatThrownBy(() -> MemberFixture.createMember("song min gyu", 21))
                .isInstanceOf(CustomException.class)
                .hasMessageContaining(INVALID_NAME.getMessage());
    }

    @Test
    void 적정이름검증_실패_특수기호있을경우(){

        // when
        Assertions.assertThatThrownBy(() -> MemberFixture.createMember("송민규임!", 21))
                .isInstanceOf(CustomException.class)
                .hasMessageContaining(INVALID_NAME.getMessage());
    }
}
