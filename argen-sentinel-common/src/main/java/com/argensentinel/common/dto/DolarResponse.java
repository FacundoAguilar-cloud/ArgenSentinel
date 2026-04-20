package com.argensentinel.common.dto;

import java.math.BigDecimal;
import java.time.Instant;

public record DolarResponse(
    String type,
    BigDecimal price,
    Instant timestamp
) {
}
