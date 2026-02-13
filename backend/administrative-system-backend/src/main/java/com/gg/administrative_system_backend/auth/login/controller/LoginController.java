package com.gg.administrative_system_backend.auth.login.controller;

import com.gg.administrative_system_backend.auth.login.dto.LoginDTO;
import com.gg.administrative_system_backend.auth.service.LoginService;
import com.gg.administrative_system_backend.response.success.ApiResponse;
import com.gg.administrative_system_backend.shared.message.GenericMessage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller that handles authentication requests.<br>
 * Provides an endpoint for user login and session generation.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class LoginController {
    private final LoginService loginService;

    /***
     * Authenticates a user and creates a session.
     *
     * @param loginDTO The login request containing user credentials.
     * @param request The HTTP request used to obtain the session.
     * @return A {@link ResponseEntity} with a success message if authentication is valid.
     */
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<?>> login(@Valid @RequestBody LoginDTO loginDTO, HttpServletRequest request) {
        loginService.generateSession(loginDTO, request);
        return ResponseEntity.status(200).body(ApiResponse.of(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), GenericMessage.LOGGED.getMessage()));
    }
}
