package com.gg.administrative_system_backend.auth.service;

import com.gg.administrative_system_backend.auth.login.dto.LoginDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Service;
/**
 * Service that handles authentication and session management. <br>
 * Uses Spring Security's {@link AuthenticationManager} to validate credentials  and creates a security context for the authenticated user.
 */
@Service
@RequiredArgsConstructor
public class LoginService {
    private final AuthenticationManager authenticationManager;

    /**
     * Authenticates the user with the provided credentials and initializes a session.
     * @param loginDTO The login request containing RFC and password.
     * @param request The HTTP request used to create or retrieve the session.
     */
    public void generateSession(LoginDTO loginDTO, HttpServletRequest request){
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getRfc(), loginDTO.getPassword()));
        context.setAuthentication(authentication);
        HttpSession session = request.getSession(true);
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, context);
    }
}
