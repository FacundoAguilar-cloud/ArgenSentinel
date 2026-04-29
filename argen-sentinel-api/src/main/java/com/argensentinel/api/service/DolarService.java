package com.argensentinel.api.service;

import com.argensentinel.api.repository.DolarPriceRepository;
import com.argensentinel.api.repository.PriceHistoryRepository;
import com.argensentinel.common.dto.DolarResponse;
import com.argensentinel.common.dto.PriceResponse;
import com.argensentinel.common.entity.DolarPrice;
import com.argensentinel.common.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DolarService {

    private final DolarPriceRepository dolarPriceRepository;
    private final PriceHistoryRepository priceHistoryRepository;

    public List<DolarResponse> getAllDollarPrices(){
        return dolarPriceRepository.findAll()
                .stream().map(this::toDolarResponse)
                .collect(Collectors.toList());

    }

    public DolarResponse getDolarByType(String type){
        try{
            DolarPrice.DolarType dolarType = DolarPrice.DolarType.valueOf(type.toUpperCase());
            DolarPrice dolarPrice = dolarPriceRepository.findTopByTypeOrderByTimestampDesc(dolarType
            ).orElseThrow(() -> new ResourceNotFoundException("Dolar price not found for type " + type));
            return toDolarResponse(dolarPrice);
        } catch (IllegalArgumentException e){
            throw new ResourceNotFoundException("Invalid dolar type: " + type);
        }
    }

    private DolarResponse toDolarResponse(DolarPrice dolarPrice) {
        return new DolarResponse(
            dolarPrice.getType().name(),
            dolarPrice.getPrice(),
            dolarPrice.getTimestamp()
        );
    }

}
