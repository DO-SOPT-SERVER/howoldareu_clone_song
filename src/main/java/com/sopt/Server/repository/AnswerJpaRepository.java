package com.sopt.Server.repository;

import com.sopt.Server.domain.Answer;
import com.sopt.Server.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AnswerJpaRepository extends JpaRepository<Answer, Long>{
    Optional<Answer> findByQuestionAndAnswerType(Question question, boolean answerType);
}
