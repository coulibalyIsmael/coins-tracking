package com.coolismo.coins_service.application.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record CoinDto(
    String id,
    LocalDateTime inserted_at,
    BigDecimal price,
    String logoUrl,
    String symbol,
    String name) {}
