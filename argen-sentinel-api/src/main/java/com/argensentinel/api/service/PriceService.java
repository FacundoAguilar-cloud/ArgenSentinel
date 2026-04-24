package com.argensentinel.api.service;

import com.argensentinel.api.repository.CryptoPriceRepository;
import com.argensentinel.api.repository.PriceHistoryRepository;
import com.argensentinel.common.dto.PriceResponse;
import com.argensentinel.common.dto.SpreadResponse;
import com.argensentinel.common.entity.CryptoPrice;
import com.argensentinel.common.entity.PriceHistory;
import com.argensentinel.common.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PriceService {

    private final CryptoPriceRepository cryptoPriceRepository;
    private final PriceHistoryRepository priceHistoryRepository;

    public List<PriceResponse> getAllPrices() {
        return cryptoPriceRepository.findTop10ByOrderByTimestampDesc()
                .stream()
                .map(this::toPriceResponse)
                .collect(Collectors.toList());
    }

    public PriceResponse getPriceBySymbol(String symbol) {
        List<CryptoPrice> prices = cryptoPriceRepository.findBySymbolOrderByTimestampDesc(symbol);
        if (prices.isEmpty()) {
            throw new ResourceNotFoundException("Price not found for symbol: " + symbol);
        }
        return toPriceResponse(prices.get(0));
    }

    public List<PriceResponse> getPricesBySource(String source) {
        return cryptoPriceRepository.findBySource(source)
                .stream()
                .map(this::toPriceResponse)
                .collect(Collectors.toList());
    }

    public List<SpreadResponse> getSpreadList() {
        List<PriceHistory> historyList = priceHistoryRepository.findTop100ByOrderByTimestampDesc();
        return historyList.stream()
                .map(this::toSpreadResponse)
                .collect(Collectors.toList());
    }

    public SpreadResponse getSpreadBySymbol(String symbol) {
        List<PriceHistory> history = priceHistoryRepository.findBySymbolOrderByTimestampDesc(symbol);
        if (history.isEmpty()) {
            throw new ResourceNotFoundException("Spread not found for symbol: " + symbol);
        }
        return toSpreadResponse(history.get(0));
    }

    private PriceResponse toPriceResponse(CryptoPrice cryptoPrice) {
        return new PriceResponse(
                cryptoPrice.getId(),
                cryptoPrice.getSymbol(),
                cryptoPrice.getPrice(),
                cryptoPrice.getSource(),
                cryptoPrice.getTimestamp()
        );
    }

    private SpreadResponse toSpreadResponse(PriceHistory history) {
        return new SpreadResponse(
                history.getSymbol(),
                history.getPriceUsd(),
                history.getPriceArs(),
                history.getSpreadPercent(),
                history.getTimestamp()
        );
    }
}