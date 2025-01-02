package com.coolismo.coins_service.application;

import com.coolismo.coins_service.application.dto.CoinDto;
import com.coolismo.coins_service.application.dto.CoinHistoryDto;
import com.coolismo.coins_service.application.mapper.CoinDtoMapper;
import com.coolismo.coins_service.application.mapper.CoinHistoryDtoMapper;
import com.coolismo.coins_service.domain.coins.CoinsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1/coins")
@RequiredArgsConstructor
public class CoinServiceControllerV1 {

  private final CoinsService coinsService;
  private final CoinDtoMapper coinDtoMapper;
  private final CoinHistoryDtoMapper coinHistoryDtoMapper;

  @GetMapping(
      value = "/{id}",
      produces = {"application/json"})
  public Mono<CoinDto> getCoinById(@PathVariable("id") String id) {
    return coinsService.getCoinById(id).map(coinDtoMapper::map);
  }

  @GetMapping(
      value = "/{id}/all",
      produces = {"application/json"})
  public Mono<CoinHistoryDto> getCoinsById(@PathVariable("id") String id) {
    return coinsService.getCoinHistory(id).map(coinHistoryDtoMapper::map);
  }
}
