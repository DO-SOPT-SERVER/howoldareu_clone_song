package com.sopt.Server.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.google.gson.Gson;
import com.sopt.Server.common.ApiResponse;
import com.sopt.Server.controller.request.MemberPostRequest;
import com.sopt.Server.controller.response.MemberGetResponse;
import com.sopt.Server.exception.Success;
import com.sopt.Server.service.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
public class MemberControllerTest {
    @InjectMocks
    private MemberController memberController;

    @Mock
    private MemberService memberService;

    private MockMvc mockMvc;
    private Gson gson;

    private String BASE_URL = "/member";

    @BeforeEach
    public void init() {
        gson = new Gson();
        mockMvc = MockMvcBuilders.standaloneSetup(memberController)
                .build();
    }

    @Test
    void 멤버저장_성공() throws Exception {
        // given
        int age = 20;
        String nickname = "송민규짱짱맨";
        MemberPostRequest memberPostRequest = new MemberPostRequest(nickname, age);

        MemberGetResponse memberGetResponse = new MemberGetResponse(1L, nickname, age);
        ApiResponse<MemberGetResponse> response = ApiResponse.success(Success.GET_MEMBER_SUCCESS, memberGetResponse);
        doReturn(response).when(memberService).saveMember(nickname, age);

        // when
        final ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post(BASE_URL)
                        .content(gson.toJson(memberPostRequest))
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // then
        resultActions.andExpect(status().isOk());
    }
}
