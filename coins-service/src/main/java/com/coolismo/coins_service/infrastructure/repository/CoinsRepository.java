package com.coolismo.coins_service.infrastructure.repository;

import com.coolismo.coins_service.infrastructure.model.CoinEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface CoinsRepository extends ReactiveCrudRepository<CoinEntity, String> {}
