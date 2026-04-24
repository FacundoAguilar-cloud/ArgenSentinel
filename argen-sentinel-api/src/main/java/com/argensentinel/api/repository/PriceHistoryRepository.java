package com.argensentinel.api.repository;

import com.argensentinel.common.entity.PriceHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PriceHistoryRepository extends JpaRepository<PriceHistory, Long> {

    Optional<PriceHistory> findBySymbol(String symbol);

    List<PriceHistory> findBySymbolOrderByTimestampDesc(String symbol);

    List<PriceHistory> findTop100ByOrderByTimestampDesc();

    List<PriceHistory> findBySymbolAndTimestampBetweenOrderByTimestampDesc(
            String symbol, java.time.Instant start, java.time.Instant end);
}