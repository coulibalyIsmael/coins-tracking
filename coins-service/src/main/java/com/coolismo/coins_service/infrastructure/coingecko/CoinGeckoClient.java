package com.coolismo.coins_service.infrastructure.coingecko;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Component
@RequiredArgsConstructor
public class CoinGeckoClient {
  private final WebClient webClient;

  @Value("${app.ws.coingecko.token}")
  private String token;

  @Value("${app.ws.coingecko.url}")
  private String host;

  public Flux<CoinGeckoMarket> getCoinsMarketData(String vsCurrency) {
    return webClient
        .get()
        .uri(
            uriBuilder ->
                uriBuilder
                    .scheme("https")
                    .host(host)
                    .path("/api/v3/coins/markets")
                    .queryParam("vs_currency", vsCurrency)
                    .build())
        .header("x-cg-pro-api-key", token)
        .retrieve()
        .bodyToFlux(CoinGeckoMarket.class)
        .subscribeOn(Schedulers.boundedElastic());
  }

  public Flux<CoinGecko> getCoinsList() {
    return webClient
        .get()
        .uri(
            uriBuilder ->
                uriBuilder
                    .scheme("https")
                    .host(host)
                    .path("/api/v3/coins/list")
                    .queryParam("status", "active")
                    .build())
        .header("x-cg-pro-api-key", token)
        .retrieve()
        .bodyToFlux(CoinGecko.class)
        .subscribeOn(Schedulers.boundedElastic());
  }
}
