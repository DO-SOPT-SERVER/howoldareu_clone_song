package com.sopt.Server.controller.response;

public record GetQuestionResponseDTO(Long questionId, String questionContent) {
    static public GetQuestionResponseDTO of(Long questionId, String questionContent) {
        return new GetQuestionResponseDTO(questionId, questionContent);
    }
}
