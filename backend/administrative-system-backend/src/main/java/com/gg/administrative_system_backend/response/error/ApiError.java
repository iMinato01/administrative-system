package com.gg.administrative_system_backend.response.error;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
@Getter
@Setter
@Builder
public class ApiError {
    private int status;
    private String error;
    private String message;
    private String path;
    @Builder.Default
    private LocalDateTime timestamp = LocalDateTime.now();
    List<String> details;
    public static ApiError of(int status, String error, String message, String path, List<String> details){
        return ApiError.builder()
                .error(error)
                .status(status)
                .message(message)
                .path(path)
                .details(details)
                .build();
    }
    public static ApiError of(int status, String error, String message, String path){
        return of(status, error, message, path, null);
    }
}
