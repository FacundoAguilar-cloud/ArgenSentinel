package com.argensentinel.common.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "user_alerts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserAlert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String userId;

    @Column(nullable = false, length = 10)
    private String symbol;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private AlertType type; //aca lo mismo, clavamos enum

    @Column(nullable = false, precision = 24, scale = 8)
    private BigDecimal targetPrice;

    @Column(nullable = false)
    private Boolean active;

    @Column
    private Instant triggeredAt;

    @Column(nullable = false)
    private Instant createdAt;

    public enum AlertType {
        ABOVE, //precio sube por encima del target
        BELOW  //precio baja  del target
    }

    @PrePersist
    protected void onCreate() {
        if (createdAt == null) {
            createdAt = Instant.now();
        }
        if (active == null) {
            active = true;
        }
    }
}
