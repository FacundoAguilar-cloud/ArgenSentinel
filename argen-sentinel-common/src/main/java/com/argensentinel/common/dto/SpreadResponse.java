package com.argensentinel.common.dto;

import java.math.BigDecimal;
import java.time.Instant;

public record SpreadResponse(
    String symbol,
    BigDecimal priceUsd,
    BigDecimal priceArs,
    BigDecimal spreadPercent,
    Instant timestamp
) {
}
