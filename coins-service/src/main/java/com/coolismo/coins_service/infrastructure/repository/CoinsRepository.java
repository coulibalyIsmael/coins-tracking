package com.coolismo.coins_service.infrastructure.repository;

import com.coolismo.coins_service.infrastructure.model.CoinEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CoinsRepository extends ReactiveCrudRepository<CoinEntity, String> {

  @Query("SELECT * FROM coins WHERE coin_id = :coinId order by inserted_at desc limit 1")
  Mono<CoinEntity> findLatestCoinInfo(String coinId);

  @Query("SELECT * FROM coins WHERE coin_id = :coinId order by inserted_at desc")
  Flux<CoinEntity> findByCoinIdOrderByInsertedAtDesc(String coinId);
}
