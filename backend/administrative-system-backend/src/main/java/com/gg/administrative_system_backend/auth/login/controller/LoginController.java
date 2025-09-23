package com.gg.administrative_system_backend.auth.login.controller;

import com.gg.administrative_system_backend.auth.login.dto.LoginDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class LoginController {
    private final AuthenticationManager authenticationManager;
    @GetMapping("/ping")
    public ResponseEntity<String> ping(){
        return ResponseEntity.status(200).body("OK");
    }

    @PostMapping
    public ResponseEntity<String> login(@Valid @RequestBody LoginDTO loginDTO){
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getRfc(), loginDTO.getPassword()));
        SecurityContext securityContext = SecurityContextHolder.getContext();
        HttpSession session = request.getSession(true);
        securityContext.setAuthentication(authentication);
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, securityContext);
        System.out.println(SecurityContextHolder.getContext().getAuthentication());
        return ResponseEntity.status(200).body("OK");
    }
}
