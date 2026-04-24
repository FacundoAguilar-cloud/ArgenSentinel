package com.argensentinel.api.repository;

import com.argensentinel.common.entity.CryptoPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CryptoPriceRepository extends JpaRepository<CryptoPrice, Long> {

    Optional<CryptoPrice> findBySymbolAndSource(String symbol, String source);

    List<CryptoPrice> findBySymbolOrderByTimestampDesc(String symbol);

    List<CryptoPrice> findBySource(String source);

    List<CryptoPrice> findTop10ByOrderByTimestampDesc();
}