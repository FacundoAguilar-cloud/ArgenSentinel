package com.argensentinel.api.repository;

import com.argensentinel.common.entity.UserAlert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserAlertRepository extends JpaRepository<UserAlert, Long> {

    Optional<UserAlert> findByIdAndUserId(Long id, String userId);

    List<UserAlert> findByUserIdAndActive(String userId, Boolean active);

    List<UserAlert> findBySymbolAndActive(String symbol, Boolean active);
}