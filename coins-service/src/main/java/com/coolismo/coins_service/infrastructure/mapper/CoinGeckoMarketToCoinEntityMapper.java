package com.coolismo.coins_service.infrastructure.mapper;


import com.coolismo.coins_service.infrastructure.coingecko.CoinGeckoMarket;
import com.coolismo.coins_service.infrastructure.model.CoinEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CoinGeckoMarketToCoinEntityMapper {

    CoinEntity map(CoinGeckoMarket coinGeckoMarket);
}
