package com.coolismo.coins_service.application.dto;

import java.util.List;

public record CoinHistoryDto(List<CoinDto> coins, int size) {}
