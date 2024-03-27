package com.sopt.Server.fixture;

import com.sopt.Server.domain.Question;

public class QuestionFixture {

    public static Question createQuestion(String question){
        return new Question(question);
    }
}
