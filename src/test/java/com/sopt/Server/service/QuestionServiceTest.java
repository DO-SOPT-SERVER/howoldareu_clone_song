package com.sopt.Server.service;

import static org.mockito.Mockito.doReturn;

import com.sopt.Server.controller.response.GetQuestionResponseDTO;
import com.sopt.Server.domain.Question;
import com.sopt.Server.repository.MemberJpaRepository;
import com.sopt.Server.repository.QuestionJpaRepository;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class QuestionServiceTest {
    @InjectMocks
    private QuestionService questionService;

    @Mock
    private QuestionJpaRepository questionJpaRepository;

    @Test
    void 질문목록조회_성공() {
        // given
        Question question1 = new Question("사랑은 늘 도망가");
        Question question2 = new Question("깡 소주를 좋아한다.");

        List<Question> questionList = List.of(question1, question2);
        doReturn(questionList).when(questionJpaRepository).findAll();

        // when
        List<GetQuestionResponseDTO> list = questionService.getQuestionResponseDTOList();

        // then
        for (int i = 0; i < list.size(); i++) {
            Assertions.assertThat(
                    list.get(i).questionContent()).isEqualTo(questionList.get(i).getQuestionContent()
                    );
        }
    }
}
