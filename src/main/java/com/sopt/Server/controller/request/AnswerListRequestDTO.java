package com.sopt.Server.controller.request;

import java.util.List;

public record AnswerListRequestDTO(String nickname, List<AnswerRequestDTO> results) {
}
