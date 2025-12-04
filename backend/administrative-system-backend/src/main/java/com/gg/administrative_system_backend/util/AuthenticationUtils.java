package com.gg.administrative_system_backend.util;

import com.gg.administrative_system_backend.exception.AuthenticationException;
import com.gg.administrative_system_backend.shared.message.ExceptionMessage;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthenticationUtils {
    public static Authentication getAuthentication(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || !authentication.isAuthenticated() || authentication instanceof AnonymousAuthenticationToken){
            throw new AuthenticationException(ExceptionMessage.AUTHENTICATION.format(authentication.getPrincipal()));
        }
        return authentication;
    }
}
