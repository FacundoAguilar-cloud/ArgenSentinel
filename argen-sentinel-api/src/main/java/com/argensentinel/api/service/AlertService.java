package com.argensentinel.api.service;

import com.argensentinel.api.repository.PriceHistoryRepository;
import com.argensentinel.api.repository.UserAlertRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AlertService {

    private final UserAlertRepository userAlertRepository;
    private final PriceHistoryRepository priceHistoryRepository;
}
