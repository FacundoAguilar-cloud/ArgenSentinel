package com.argensentinel.common.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "price_history")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PriceHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 10)
    private String symbol;

    @Column(nullable = false, precision = 24, scale = 8)
    private BigDecimal priceUsd;

    @Column(precision = 24, scale = 8)
    private BigDecimal priceArs;

    @Column(precision = 12, scale = 6)
    private BigDecimal spreadPercent;

    @Column(nullable = false)
    private Instant timestamp;

    @PrePersist
    protected void onCreate(){
        if (timestamp == null){
            timestamp = Instant.now();
        }
    }
}
