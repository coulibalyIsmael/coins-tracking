package com.coolismo.coins_service.infrastructure.coingecko;

import com.coolismo.coins_service.infrastructure.mapper.CoinGeckoMarketToCoinEntityMapper;
import com.coolismo.coins_service.infrastructure.model.CoinEntity;
import com.coolismo.coins_service.infrastructure.repository.CoinsRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CoinGeckoSpiImpl {

  private final CoinGeckoClient coingeckoClient;
  private final CoinGeckoMarketToCoinEntityMapper coinGeckoMarketToCoinEntityMapper;
  private final CoinsRepository coinsRepository;

  public Mono<List<CoinEntity>> addNewCoinsFromCoinGecko() {
    return coingeckoClient
        .getCoinsMarketData("eur")
        .map(coinGeckoMarketToCoinEntityMapper::map)
        .collectList()
        .flatMap(coinEntities -> coinsRepository.saveAll(coinEntities).collectList());
  }
}
