package com.argensentinel.api.service;

import com.argensentinel.api.repository.PriceHistoryRepository;
import com.argensentinel.api.repository.UserAlertRepository;
import com.argensentinel.common.dto.AlertRequest;
import com.argensentinel.common.dto.AlertResponse;
import com.argensentinel.common.entity.UserAlert;
import com.argensentinel.common.exception.BadRequestException;
import com.argensentinel.common.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AlertService {

    private final UserAlertRepository userAlertRepository;
    private final PriceHistoryRepository priceHistoryRepository;

    public List <AlertResponse> getAlertsByUser(String userId){
        return userAlertRepository.findByUserIdAndActive(userId, true)
                .stream()
                .map(this::toAlertResponse)
                .collect(Collectors.toList());
}

    public AlertResponse createAlert(String userId, AlertRequest request){
        validateAlertRequest(request);

        UserAlert alert = UserAlert.builder()
                .userId(userId)
                .symbol(request.symbol().toUpperCase())
                .type(UserAlert.AlertType.valueOf(request.alertType().toUpperCase()))
                .targetPrice(new java.math.BigDecimal(request.targetPrice()))
                .active(true)
                .createdAt(Instant.now())
                .build();
        UserAlert saved = userAlertRepository.save(alert);
        return toAlertResponse(saved);
    }

    public void deleteAlert(Long alertId, String userId){
        UserAlert alert = userAlertRepository.findByIdAndUserId(alertId, userId)
            .orElseThrow(() -> new ResourceNotFoundException("Alert not found"));

            userAlertRepository.delete(alert);
        }

    private void validateAlertRequest(AlertRequest request){
        if(request.symbol() == null || request.symbol().isBlank() ){
            throw new BadRequestException("Symbol is required");

        }
        if(request.alertType() == null || request.alertType().isBlank() ){
            throw new BadRequestException("Alert type is required");
        }
        if(request.targetPrice() == null || request.targetPrice().compareTo(String.valueOf(BigDecimal.ZERO)) <= 0){
            throw new BadRequestException("Target price is required");
        }


    }

    private AlertResponse toAlertResponse(UserAlert alert){
        return new AlertResponse(
            alert.getId(),
            alert.getSymbol(),
            alert.getType().name(),
            alert.getTargetPrice(),
            alert.getActive(),
            alert.getTriggeredAt(),
            alert.getCreatedAt()
        );
    }


}