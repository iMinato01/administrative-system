package com.gg.administrative_system_backend.order.report;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderReportController {
    private final OrderReportService orderReportService;
    @GetMapping("/report/{id}")
    public ResponseEntity<byte[]> getReport(@PathVariable Long id) throws Exception{
        return ResponseEntity.status(200)
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION)
                .body(orderReportService.exportPdf(id));
    }
}
