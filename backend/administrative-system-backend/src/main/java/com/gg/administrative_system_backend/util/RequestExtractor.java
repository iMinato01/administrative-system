package com.gg.administrative_system_backend.util;

import jakarta.servlet.http.HttpServletRequest;

public class RequestExtractor {
    public static String ip(HttpServletRequest request){
        return request.getRemoteAddr();
    }

    public static String user(HttpServletRequest request){
        return request.getUserPrincipal() == null? "unknow": request.getUserPrincipal().getName();
    }
}
