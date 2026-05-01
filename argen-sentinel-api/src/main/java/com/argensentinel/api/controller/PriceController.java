package com.argensentinel.api.controller;

import com.argensentinel.api.service.PriceService;
import com.argensentinel.common.dto.PriceResponse;
import com.argensentinel.common.dto.SpreadResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/prices")
@RequiredArgsConstructor
public class PriceController {

    private final PriceService priceService;
    @GetMapping
    public ResponseEntity<List<PriceResponse>> getAllPrices() {
        return ResponseEntity.ok(priceService.getAllPrices());
    }

    @GetMapping("/{symbol}")
    public ResponseEntity<PriceResponse> getPriceBySymbol(@PathVariable String symbol) {
        return ResponseEntity.ok(priceService.getPriceBySymbol(symbol.toUpperCase()));
    }

    @GetMapping("/by-source")
    public ResponseEntity<List<PriceResponse>> getPricesBySource(@PathVariable String source) {
        return ResponseEntity.ok(priceService.getPricesBySource(source));
    }
    @GetMapping("/spread")
    public ResponseEntity<List<SpreadResponse>> getSpreadList(){
        return ResponseEntity.ok(priceService.getSpreadList());
    }

    @GetMapping("/spread/{symbol}")
    public ResponseEntity <SpreadResponse> getSpreadBySymbol(@PathVariable String symbol) {
        return ResponseEntity.ok(priceService.getSpreadBySymbol(symbol.toUpperCase()));
    }


}
