package com.sopt.Server.controller.response;

public record ResultResponseDTO(String nickname, int resultAge, String title, String content, String imgUrl1, String imgUrl2) {
    public static ResultResponseDTO of(String nickname, int resultAge, String title, String content, String imgUrl1, String imgUrl2) {
        return new ResultResponseDTO(nickname, resultAge, title, content, imgUrl1, imgUrl2);
    }
}
