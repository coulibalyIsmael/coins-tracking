package com.coolismo.coins_service.domain.coins.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class Coin {
  private String id;
  private LocalDateTime inserted_at;
  private BigDecimal price;
  private String logUrl;
  private String symbol;
  private String name;
}
