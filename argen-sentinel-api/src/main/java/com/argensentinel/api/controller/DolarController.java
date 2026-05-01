package com.argensentinel.api.controller;

import com.argensentinel.api.service.DolarService;
import com.argensentinel.common.dto.DolarResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/dolar")
@RequiredArgsConstructor
public class DolarController {

    private final DolarService dolarService;

    @GetMapping
    public ResponseEntity<List<DolarResponse>> getAllDollarPrices() {
        return ResponseEntity.ok(dolarService.getAllDollarPrices());
    }

    @GetMapping("/{type}")
    public ResponseEntity <DolarResponse> getDolarByType(@PathVariable String type) {
        return ResponseEntity.ok(dolarService.getDolarByType(type));
    }

}
