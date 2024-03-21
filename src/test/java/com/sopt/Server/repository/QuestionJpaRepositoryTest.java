package com.sopt.Server.repository;

import com.sopt.Server.domain.Question;
import com.sopt.Server.fixture.QuestionFixture;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class QuestionJpaRepositoryTest {

    @Autowired
    private QuestionJpaRepository questionJpaRepository;

    @Test
    void 질문저장_성공_정상입력(){
        // given
        Question question = QuestionFixture.createQuestion("이건 질문입니다만");

        // when
        Question savedQ = questionJpaRepository.save(question);

        // then
        Assertions.assertThat(savedQ.getQuestionContent()).isEqualTo(question.getQuestionContent());
    }
}
