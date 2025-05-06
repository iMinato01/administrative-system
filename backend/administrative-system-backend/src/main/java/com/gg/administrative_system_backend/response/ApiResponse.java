package com.gg.administrative_system_backend.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ApiResponse<T> {
    private int status;
    private String message;
    private T data;
    public static <T> ApiResponse<T> of(int status, String message, T data){
        return ApiResponse.<T>builder()
                .status(status)
                .message(message)
                .data(data)
                .build();
    }
}
