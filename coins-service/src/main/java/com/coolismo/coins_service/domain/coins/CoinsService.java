package com.coolismo.coins_service.domain.coins;

import com.coolismo.coins_service.infrastructure.coingecko.CoinGeckoClient;
import com.coolismo.coins_service.infrastructure.mapper.CoinGeckoMarketToCoinEntityMapper;
import com.coolismo.coins_service.infrastructure.repository.CoinsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class CoinsService {
  private final CoinGeckoClient coingeckoClient;
  private final CoinGeckoMarketToCoinEntityMapper coinGeckoMarketToCoinEntityMapper;
  private final CoinsRepository coinsRepository;

  public Mono<Void> getCoinsList() {
    coingeckoClient
        .getCoinsList("eur")
        .map(coinGeckoMarketToCoinEntityMapper::map)
        .collectList()
        .flatMap(coinEntities -> coinsRepository.saveAll(coinEntities).collectList())
        .block();
    return Mono.empty();
  }
}
