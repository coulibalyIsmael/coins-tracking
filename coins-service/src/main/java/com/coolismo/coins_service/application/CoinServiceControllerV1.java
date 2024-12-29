package com.coolismo.coins_service.application;

import com.coolismo.coins_service.domain.coins.CoinsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/coins")
@RequiredArgsConstructor
public class CoinServiceControllerV1 {

    private final CoinsService coinsService;

  @GetMapping(
      value = "/list",
      produces = {"application/json", "application/text"})
  public String getCoinsList() {
        coinsService.getCoinsList().block();
        return "Coins list";
    }
}
