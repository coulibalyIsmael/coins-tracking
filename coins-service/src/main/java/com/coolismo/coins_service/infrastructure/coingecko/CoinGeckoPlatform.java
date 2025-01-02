package com.coolismo.coins_service.infrastructure.coingecko;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CoinGeckoPlatform(String ethereum, @JsonProperty("polygon-pos") String polygonPos) {}
