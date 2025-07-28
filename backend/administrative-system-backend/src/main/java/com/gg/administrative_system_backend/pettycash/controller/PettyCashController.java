package com.gg.administrative_system_backend.pettycash.controller;

import com.gg.administrative_system_backend.pettycash.dto.CreatePettyCashDTO;
import com.gg.administrative_system_backend.pettycash.entity.PettyCash;
import com.gg.administrative_system_backend.pettycash.service.PettyCashService;
import com.gg.administrative_system_backend.response.success.ApiResponse;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@RequestMapping("/pettycash")
public class PettyCashController {
    private final PettyCashService pettyCashService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<PettyCash>>> findAll() {
        return ResponseEntity.status(200).body(ApiResponse.of(HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase(),
                pettyCashService.findAll()));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<PettyCash>> savePettyCash(@Valid @RequestBody CreatePettyCashDTO createPettyCashDTO) {
        return ResponseEntity.status(200).body(ApiResponse.of(HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase(),
                pettyCashService.savePettyCash(createPettyCashDTO)));
    }
}
