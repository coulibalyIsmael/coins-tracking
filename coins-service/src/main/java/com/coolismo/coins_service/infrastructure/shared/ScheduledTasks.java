package com.coolismo.coins_service.infrastructure.shared;

import com.coolismo.coins_service.infrastructure.coingecko.CoinGeckoSpiImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ScheduledTasks {

  private final CoinGeckoSpiImpl coinGeckoSpi;

  @Async
  @Scheduled(initialDelay = 10000L, fixedRateString = "${app.scheduled-tasks.coingecko-fixed-rate}")
  public void getCoinGeckoData() {
    log.info("Getting CoinGecko data");

    coinGeckoSpi
        .addNewCoinsFromCoinGecko()
        .subscribe(
            coinGecko -> {
              log.info("CoinGecko data saved size: {}", coinGecko.size());
            });
  }
}
