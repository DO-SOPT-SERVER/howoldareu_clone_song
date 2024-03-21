package com.sopt.Server.exception.model;

import com.sopt.Server.exception.Error;

public class CustomException extends RuntimeException {
    private final Error error;
    public CustomException(Error error, String message) {
        super(message);
        this.error = error;
    }

    public int getHttpStatus() {
        return error.getHttpStatus();
    }
}
