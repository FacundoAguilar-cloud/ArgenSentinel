package com.argensentinel.common.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "dolar_prices")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DolarPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private DolarType type; //vamos a tener que crear un enum para esto (asumo)

    @Column(nullable = false, precision = 14, scale = 2)
    private BigDecimal price;

    @Column(nullable = false)
    private Instant timestamp;

    public enum DolarType {
        OFICIAL,
        BLUE,
        MEP,
        TARJETA
    }

    @PrePersist
    protected void onCreate(){
        if (timestamp == null){
            timestamp = Instant.now();
        }
    }
}
