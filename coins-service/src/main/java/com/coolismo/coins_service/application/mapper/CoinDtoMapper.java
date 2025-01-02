package com.coolismo.coins_service.application.mapper;

import com.coolismo.coins_service.application.dto.CoinDto;
import com.coolismo.coins_service.infrastructure.model.CoinEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CoinDtoMapper {

  @Mapping(target = "price", source = "current_price")
  @Mapping(target = "logoUrl", source = "image")
  @Mapping(target = "id", source = "coin_id")
  @Mapping(target = "inserted_at", source = "inserted_at")
  CoinDto map(CoinEntity coinGecko);
}
