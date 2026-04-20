package com.argensentinel.common.dto;

public record AlertRequest(
    String symbol,
    String alertType,
    String targetPrice
) {
}
