package com.coolismo.coins_service.infrastructure.coingecko;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class CoinGeckoMarket implements Serializable {
  private String id;
  private String symbol;
  private String name;
  private BigDecimal current_price;
  private BigInteger market_cap;
  private BigInteger total_volume;
  private BigDecimal high_24h;
  private BigDecimal low_24h;
  private BigDecimal price_change_24h;
  private BigDecimal price_change_percentage_24h;
  private BigDecimal price_change_percentage_1h;
  private BigInteger market_cap_change_24h;
  private BigDecimal market_cap_change_percentage_24h;
  private BigInteger circulating_supply;
  private BigInteger total_supply;
  private BigInteger ath;
  private BigDecimal ath_change_percentage;
  private LocalDateTime ath_date;
  private BigDecimal atl;
  private BigDecimal atl_change_percentage;
  private LocalDateTime atl_date;
  private LocalDateTime last_updated;
  private String image;
  private Integer market_cap_rank;
  private BigDecimal fully_diluted_valuation;
  private BigInteger max_supply;
}
