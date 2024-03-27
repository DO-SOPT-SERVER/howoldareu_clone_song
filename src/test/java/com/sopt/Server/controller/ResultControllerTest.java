package com.sopt.Server.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.google.gson.Gson;
import com.sopt.Server.common.AgeEnum;
import com.sopt.Server.common.ApiResponse;
import com.sopt.Server.controller.request.AnswerListRequestDTO;
import com.sopt.Server.controller.request.AnswerRequestDTO;
import com.sopt.Server.controller.response.ResultResponseDTO;
import com.sopt.Server.exception.Success;
import com.sopt.Server.service.MemberService;
import com.sopt.Server.service.ResultService;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
public class ResultControllerTest {
    @InjectMocks
    private ResultController resultController;

    @Mock
    private ResultService resultService;

    private MockMvc mockMvc;
    private Gson gson;

    private String BASE_URL = "/result";

    @BeforeEach
    public void init() {
        gson = new Gson();
        mockMvc = MockMvcBuilders.standaloneSetup(resultController)
                .build();
    }

    @Test
    void 결과저장_성공() throws Exception {
        // given
        String nickname = "송민규짱짱맨";
        AgeEnum ageEnum = AgeEnum.TWENTIES;
        AnswerRequestDTO dto1 = new AnswerRequestDTO(1L, true);
        AnswerRequestDTO dto2 = new AnswerRequestDTO(2L, false);

        AnswerListRequestDTO requestDTO = new AnswerListRequestDTO(nickname, List.of(dto1, dto2));

        ResultResponseDTO resultResponseDTO = new ResultResponseDTO(nickname, 20, ageEnum.getTitle(),
                ageEnum.getContent(), ageEnum.getImageUrl1(),
                ageEnum.getImageUrl2());

        doReturn(resultResponseDTO).when(resultService).saveResult(any());

        // when
        final ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post(BASE_URL)
                        .content(gson.toJson(requestDTO))
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // then
        resultActions.andExpect(jsonPath("$.data.nickname").value("송민규짱짱맨"));
        resultActions.andExpect(jsonPath("$.data.resultAge").value(20));
        resultActions.andExpect(jsonPath("$.data.title").value(ageEnum.getTitle()));
        resultActions.andExpect(jsonPath("$.data.content").value(ageEnum.getContent()));
        resultActions.andExpect(jsonPath("$.data.imgUrl1").value(ageEnum.getImageUrl1()));
        resultActions.andExpect(jsonPath("$.data.imgUrl2").value(ageEnum.getImageUrl2()));

    }

}
