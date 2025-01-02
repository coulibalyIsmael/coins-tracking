package com.coolismo.coins_service.domain.coins.model;

import com.coolismo.coins_service.infrastructure.model.CoinEntity;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CoinHistory {
  private List<CoinEntity> coins;
  private int size;
}
