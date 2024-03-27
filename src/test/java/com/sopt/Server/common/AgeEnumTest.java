package com.sopt.Server.common;

import static com.sopt.Server.exception.Error.INVALID_AGE_ENUM;
import static com.sopt.Server.exception.Error.INVALID_NAME;

import com.sopt.Server.exception.model.CustomException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class AgeEnumTest {

    @Test
    void 연령Enum조회_성공_정상입력(){
        // given
        int age = 20;

        // when
        AgeEnum ageEnum = AgeEnum.getAgeEnum(age);

        // then
        Assertions.assertThat(ageEnum.getMinAge()).isEqualTo(20);
    }

    @Test
    void 연령Enum조회_예외발생_연령대가10대인경우(){
        // given
        int age = 19;

        // when
        Assertions.assertThatThrownBy(() -> AgeEnum.getAgeEnum(age))
                .isInstanceOf(CustomException.class)
                .hasMessageContaining(INVALID_AGE_ENUM.getMessage());
    }
}
