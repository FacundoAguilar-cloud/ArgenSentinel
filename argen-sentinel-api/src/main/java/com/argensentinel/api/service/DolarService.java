package com.argensentinel.api.service;

import com.argensentinel.api.repository.DolarPriceRepository;
import com.argensentinel.api.repository.PriceHistoryRepository;
import com.argensentinel.common.dto.PriceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DolarService {

    private final DolarPriceRepository dolarPriceRepository;
    private final PriceHistoryRepository priceHistoryRepository;

    public List<PriceResponse> findByType(){

    }
}
