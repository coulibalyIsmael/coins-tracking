package com.coolismo.coins_service.infrastructure.mapper;

import com.coolismo.coins_service.infrastructure.coingecko.CoinGeckoMarket;
import com.coolismo.coins_service.infrastructure.model.CoinEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CoinGeckoMarketToCoinEntityMapper {

  @Mapping(target = "coin_id", source = "id")
  @Mapping(target = "id", ignore = true)
  CoinEntity map(CoinGeckoMarket coinGeckoMarket);
}
