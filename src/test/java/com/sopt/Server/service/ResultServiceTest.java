package com.sopt.Server.service;

import static com.sopt.Server.exception.Error.NOT_FOUND_ANSWER_EXCEPTION;
import static com.sopt.Server.exception.Error.NOT_FOUND_MEMBER_EXCEPTION;
import static com.sopt.Server.exception.Error.NOT_FOUND_QUESTION_EXCEPTION;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;

import com.sopt.Server.common.AgeEnum;
import com.sopt.Server.controller.request.AnswerListRequestDTO;
import com.sopt.Server.controller.request.AnswerRequestDTO;
import com.sopt.Server.controller.response.AllResultsResponseDTO;
import com.sopt.Server.controller.response.ResultResponseDTO;
import com.sopt.Server.domain.Answer;
import com.sopt.Server.domain.Member;
import com.sopt.Server.domain.Question;
import com.sopt.Server.domain.Result;
import com.sopt.Server.exception.model.CustomException;
import com.sopt.Server.repository.AnswerJpaRepository;
import com.sopt.Server.repository.MemberJpaRepository;
import com.sopt.Server.repository.QuestionJpaRepository;
import com.sopt.Server.repository.ResultJpaRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ResultServiceTest {

    @InjectMocks
    private ResultService resultService;

    @Mock
    private MemberJpaRepository memberJpaRepository;

    @Mock
    private QuestionJpaRepository questionJpaRepository;

    @Mock
    private AnswerJpaRepository answerJpaRepository;

    @Mock
    private ResultJpaRepository resultJpaRepository;

    private Member member;
    private Question question_1;
    private Answer answer_1;
    private Question question_2;
    private Answer answer_2;
    private Result result;

    @BeforeEach
    void init() {
        member = new Member("송민규", 20);
        question_1 = new Question("너 사랑을 알아?");
        answer_1 = Answer.builder()
                .question(question_1)
                .answerType(true)
                .answerScore(30)
                .build();

        question_2 = new Question("너 우정을 알아?");
        answer_2 = Answer.builder()
                .question(question_2)
                .answerType(true)
                .answerScore(5)
                .build();

        result = Result.builder()
                .member(member)
                .resultAge(20)
                .build();
    }

    @Test
    void 결과저장_성공() {
        // given
        AnswerRequestDTO dto_1 = new AnswerRequestDTO(1L, true);
        AnswerRequestDTO dto_2 = new AnswerRequestDTO(2L, false);

        AnswerListRequestDTO answerListRequestDTO = new AnswerListRequestDTO("송민규짱짱맨", List.of(dto_1, dto_2));

        doReturn(Optional.ofNullable(member)).when(memberJpaRepository).findByName(any());
        doReturn(result).when(resultJpaRepository).save(any());

        doReturn(Optional.ofNullable(question_1)).when(questionJpaRepository).findById(1L);
        doReturn(Optional.ofNullable(question_2)).when(questionJpaRepository).findById(2L);

        doReturn(Optional.ofNullable(answer_1)).when(answerJpaRepository).findByQuestionAndAnswerType(question_1, true);
        doReturn(Optional.ofNullable(answer_2)).when(answerJpaRepository)
                .findByQuestionAndAnswerType(question_2, false);

        // when
        ResultResponseDTO resultResponseDTO = resultService.saveResult(answerListRequestDTO);

        // then
        AgeEnum ageEnum = AgeEnum.FIFTIES;
        Assertions.assertThat(resultResponseDTO)
                .extracting("nickname", "resultAge", "title", "content", "imgUrl1", "imgUrl2")
                .containsExactly("송민규짱짱맨", 55, ageEnum.getTitle(), ageEnum.getContent(), ageEnum.getImageUrl1(),
                        ageEnum.getImageUrl2());
    }

    @Test
    void 결과저장_예외발생_존재하지않은이름일경우() {
        // given
        AnswerRequestDTO dto_1 = new AnswerRequestDTO(1L, true);
        AnswerRequestDTO dto_2 = new AnswerRequestDTO(2L, false);

        AnswerListRequestDTO answerListRequestDTO = new AnswerListRequestDTO("송민규짱짱맨", List.of(dto_1, dto_2));

        doReturn(Optional.ofNullable(null)).when(memberJpaRepository).findByName(any());

        // when, then
        Assertions.assertThatThrownBy(() -> resultService.saveResult(answerListRequestDTO))
                .isInstanceOf(CustomException.class)
                .hasMessageContaining(NOT_FOUND_MEMBER_EXCEPTION.getMessage());

    }

    @Test
    void 결과저장_예외발생_존재하지않은질문Id경우() {
        // given
        AnswerRequestDTO dto_1 = new AnswerRequestDTO(-1L, true);
        AnswerRequestDTO dto_2 = new AnswerRequestDTO(-2L, false);

        AnswerListRequestDTO answerListRequestDTO = new AnswerListRequestDTO("송민규짱짱맨", List.of(dto_1, dto_2));

        doReturn(Optional.ofNullable(member)).when(memberJpaRepository).findByName(any());

        // when, then
        Assertions.assertThatThrownBy(() -> resultService.saveResult(answerListRequestDTO))
                .isInstanceOf(CustomException.class)
                .hasMessageContaining(NOT_FOUND_QUESTION_EXCEPTION.getMessage());
    }

    @Test
    void 결과저장_예외발생_존재하지않은답변Id경우() {
        // given
        AnswerRequestDTO dto_1 = new AnswerRequestDTO(1L, true);
        AnswerRequestDTO dto_2 = new AnswerRequestDTO(2L, true);

        AnswerListRequestDTO answerListRequestDTO = new AnswerListRequestDTO("송민규짱짱맨", List.of(dto_1, dto_2));

        doReturn(Optional.ofNullable(member)).when(memberJpaRepository).findByName(any());
        doReturn(Optional.ofNullable(question_1)).when(questionJpaRepository).findById(1L);

        doReturn(Optional.ofNullable(null)).when(answerJpaRepository).findByQuestionAndAnswerType(question_1, true);

        // when, then
        Assertions.assertThatThrownBy(() -> resultService.saveResult(answerListRequestDTO))
                .isInstanceOf(CustomException.class)
                .hasMessageContaining(NOT_FOUND_ANSWER_EXCEPTION.getMessage());
    }

    @Test
    void 결과목록조회_성공() {
        // given
        int resultAge1 = 20;
        int resultAge2 = 25;
        AgeEnum ageEnum1 = AgeEnum.getAgeEnum(resultAge1);
        AgeEnum ageEnum2 = AgeEnum.getAgeEnum(resultAge2);


        Result result1 = Result.builder()
                .member(member)
                .resultAge(resultAge1)
                .testedDate(LocalDateTime.now())
                .build();

        Result result2 = Result.builder()
                .member(member)
                .resultAge(resultAge2)
                .testedDate(LocalDateTime.now())
                .build();

        List<Result> resultList = List.of(result1, result2);

        doReturn(resultList).when(resultJpaRepository).findAllByMemberIdOrderByIdDesc(any());

        // when
        List<AllResultsResponseDTO> allResults = resultService.getAllResults(1L);

        // then
        Assertions.assertThat(allResults).isNotNull();

        Assertions.assertThat(allResults.get(0))
                .extracting("resultAge", "title", "content", "imgUrl1", "imgUrl2")
                .containsExactly(resultAge1, ageEnum1.getTitle(),
                        ageEnum1.getContent(), ageEnum1.getImageUrl1(),
                        ageEnum1.getImageUrl2());

        Assertions.assertThat(allResults.get(1))
                .extracting("resultAge", "title", "content", "imgUrl1", "imgUrl2")
                .containsExactly(resultAge2, ageEnum2.getTitle(),
                        ageEnum2.getContent(), ageEnum2.getImageUrl1(),
                        ageEnum2.getImageUrl2());
    }
}
