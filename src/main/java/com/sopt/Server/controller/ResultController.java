package com.sopt.Server.controller;

import com.sopt.Server.common.ApiResponse;
import com.sopt.Server.controller.request.AnswerListRequestDTO;
import com.sopt.Server.controller.response.AllResultsResponseDTO;
import com.sopt.Server.controller.response.ResultResponseDTO;
import com.sopt.Server.exception.Success;
import com.sopt.Server.service.ResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/result")
@RequiredArgsConstructor
public class ResultController {
    private final ResultService resultService;

    @PostMapping("")
    public ApiResponse<ResultResponseDTO> saveResult(@RequestBody AnswerListRequestDTO answerListRequestDTO){
        return ApiResponse.success(Success.CREATE_RESULT_SUCCESS,resultService.saveResult(answerListRequestDTO));
    }

    @GetMapping("/{memberId}")
    public ApiResponse<List<AllResultsResponseDTO>> getAllResults(@PathVariable Long memberId) {
        return ApiResponse.success(Success.GET_USER_LIST_SUCCESS, resultService.getAllResults(memberId));
    }

}