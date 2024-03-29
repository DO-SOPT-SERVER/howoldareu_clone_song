package com.sopt.Server.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "ANSWERS")
@Getter
public class Answer {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerId;

    @ManyToOne
    private Question question;

    private boolean answerType;

    private int answerScore;

    @Builder
    public Answer(Question question, boolean answerType, int answerScore) {
        this.question = question;
        this.answerType = answerType;
        this.answerScore = answerScore;
    }
}
