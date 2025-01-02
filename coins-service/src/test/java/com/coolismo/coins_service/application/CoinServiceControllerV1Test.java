package com.coolismo.coins_service.application;

import static java.time.LocalDateTime.now;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.coolismo.coins_service.infrastructure.model.CoinEntity;
import com.coolismo.coins_service.infrastructure.repository.CoinsRepository;
import com.coolismo.coins_service.infrastructure.shared.ScheduledTasks;
import io.restassured.module.webtestclient.RestAssuredWebTestClient;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CoinServiceControllerV1Test {

  @MockitoSpyBean private ScheduledTasks scheduledTasks;

  @MockitoSpyBean private CoinsRepository coinsRepository;

  @Autowired private WebTestClient webTestClient;

  @LocalServerPort private int port;

  @Test
  void getCoinGeckoData_shouldReturnOk() {

    LocalDateTime localDateTime = now();

    when(coinsRepository.findLatestCoinInfo("bitcoin"))
        .thenReturn(
            Mono.just(
                CoinEntity.builder()
                    .id(BigInteger.valueOf(1))
                    .coin_id("bitcoin")
                    .name("Bitcoin")
                    .current_price(BigDecimal.valueOf(999.01))
                    .image("url")
                    .inserted_at(localDateTime)
                    .build()));
    await()
        .atMost(15, SECONDS)
        .untilAsserted(() -> verify(scheduledTasks, atLeastOnce()).getCoinGeckoData());

    RestAssuredWebTestClient.given()
        .webTestClient(webTestClient)
        .when()
        .get("http://localhost:%s/v1/coins/%s".formatted(port, "bitcoin"))
        .then()
        .statusCode(200)
        .body("id", equalTo("bitcoin"))
        .body("name", equalTo("Bitcoin"))
        .body("price", equalTo(999.01F))
        .body("logoUrl", equalTo("url"));
  }

  @Test
  void getCoinGeckoData_shouldReturnNotFound() {
    when(coinsRepository.findLatestCoinInfo("toto")).thenReturn(Mono.empty());
    await()
        .atMost(15, SECONDS)
        .untilAsserted(() -> verify(scheduledTasks, atLeastOnce()).getCoinGeckoData());

    RestAssuredWebTestClient.given()
        .webTestClient(webTestClient)
        .when()
        .get("http://localhost:%s/v1/coins/%s".formatted(port, "bitcoin"))
        .then()
        .statusCode(404);
  }
}
