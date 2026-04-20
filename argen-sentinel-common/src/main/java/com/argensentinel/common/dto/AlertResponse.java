package com.argensentinel.common.dto;

import java.math.BigDecimal;
import java.time.Instant;

public record AlertResponse(
        Long id,
        String symbol,
        String alertType,
        BigDecimal targetPrice,
        Boolean active,
        Instant triggeredAt,
        Instant createdAt) {

}
