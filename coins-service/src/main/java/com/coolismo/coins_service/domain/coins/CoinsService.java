package com.coolismo.coins_service.domain.coins;

import com.coolismo.coins_service.domain.coins.model.CoinHistory;
import com.coolismo.coins_service.infrastructure.coingecko.CoinGeckoClient;
import com.coolismo.coins_service.infrastructure.mapper.CoinGeckoMarketToCoinEntityMapper;
import com.coolismo.coins_service.infrastructure.model.CoinEntity;
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

  public Mono<CoinEntity> getCoinById(String id) {
    log.info("Getting coin with id: {}", id);
    return coinsRepository.findLatestCoinInfo(id);
  }

  public Mono<CoinHistory> getCoinHistory(String id) {
    log.info("Getting coin history with id: {}", id);
    return coinsRepository
        .findByCoinIdOrderByInsertedAtDesc(id)
        .collectList()
        .map(
            coinEntities -> {
              CoinHistory coinHistory = new CoinHistory();
              coinHistory.setCoins(coinEntities);
              coinHistory.setSize(coinEntities.size());
              return coinHistory;
            });
  }
}
