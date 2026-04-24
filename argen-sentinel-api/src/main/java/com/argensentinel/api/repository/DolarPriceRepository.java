package com.argensentinel.api.repository;

import com.argensentinel.common.entity.DolarPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DolarPriceRepository extends JpaRepository<DolarPrice, Long> {

    Optional<DolarPrice> findByType(DolarPrice.DolarType type);

    List<DolarPrice> findByTypeOrderByTimestampDesc(DolarPrice.DolarType type);

    List<DolarPrice> findTopByTypeOrderByTimestampDesc(DolarPrice.DolarType type);
}