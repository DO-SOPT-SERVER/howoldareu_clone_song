package com.sopt.Server.controller.response;

import com.sopt.Server.domain.Result;

public record AllResultsResponseDTO(Long id, int resultAge, String title, String content, String testedDate, String imgUrl1, String imgUrl2) {
    public static AllResultsResponseDTO of(Result result, String title, String content, String testedDate, String imgUrl1, String imgUrl2) {
        return new AllResultsResponseDTO(result.getId(), result.getResultAge(), title, content, testedDate, imgUrl1, imgUrl2);
    }
}