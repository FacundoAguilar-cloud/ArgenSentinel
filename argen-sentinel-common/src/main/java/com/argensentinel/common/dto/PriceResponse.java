package com.argensentinel.common.dto;

import java.math.BigDecimal;
import java.time.Instant;
//aca usamos records dado que son inmutables a diferencia de las clases normales
public record PriceResponse(
        Long id,
        String symbol,
        BigDecimal price,
        String source,
        Instant timestamp
) {

}
