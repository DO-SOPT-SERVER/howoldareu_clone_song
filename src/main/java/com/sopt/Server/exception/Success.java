package com.sopt.Server.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Success {

    // 200 OK
    GET_HEALTH_CHECK_SUCCESS(HttpStatus.OK, "서버 상태 체크에 성공했습니다"),
    GET_MEMBER_SUCCESS(HttpStatus.OK, "멤버 조회에 성공했습니다"),
    GET_QUESTION_LIST_SUCCESS(HttpStatus.OK, "질문 리스트 조회 성공"),
    GET_USER_LIST_SUCCESS(HttpStatus.OK, "유저 결과 리스트 반환 성공"),

    // 201 created
    CREATE_MEMBER_SUCCESS(HttpStatus.CREATED, "멤버 생성에 성공했습니다"),
    CREATE_RESULT_SUCCESS(HttpStatus.CREATED, "결과 생성 성공");


    private final HttpStatus httpStatus;
    private final String message;

    public int getHttpStatus(){
        return httpStatus.value();
    }

    public String getMessage(){
        return message;
    }
}
