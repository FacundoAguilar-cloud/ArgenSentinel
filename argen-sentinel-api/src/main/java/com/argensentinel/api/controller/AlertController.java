package com.argensentinel.api.controller;

import com.argensentinel.api.service.AlertService;
import com.argensentinel.common.dto.AlertRequest;
import com.argensentinel.common.dto.AlertResponse;
import com.argensentinel.common.dto.PriceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alerts")
@RequiredArgsConstructor
public class AlertController {

    private final AlertService alertService;

    @GetMapping
    public ResponseEntity<List<AlertResponse>> getMyAlerts(@AuthenticationPrincipal Jwt jwt){
        String userId = jwt.getSubject();
        return ResponseEntity.ok(alertService.getAlertsByUser(userId));
    }

    @PostMapping
    public ResponseEntity <AlertResponse> createAlert(
            @AuthenticationPrincipal Jwt jwt,
            @RequestBody AlertRequest request){
        String userId = jwt.getSubject();
        return ResponseEntity.ok(alertService.createAlert(userId, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity <Void> deleteAlert(
            @PathVariable Long id,
            @AuthenticationPrincipal Jwt jwt){
        String userId = jwt.getSubject();
        alertService.deleteAlert(id, userId);
        return ResponseEntity.noContent().build();
    }
}
