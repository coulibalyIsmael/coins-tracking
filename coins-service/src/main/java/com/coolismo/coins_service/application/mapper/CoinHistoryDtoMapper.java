package com.coolismo.coins_service.application.mapper;

import com.coolismo.coins_service.application.dto.CoinHistoryDto;
import com.coolismo.coins_service.domain.coins.model.CoinHistory;
import org.mapstruct.Mapper;

@Mapper(
    componentModel = "spring",
    uses = {CoinDtoMapper.class})
public interface CoinHistoryDtoMapper {

  CoinHistoryDto map(CoinHistory coinHistory);
}
