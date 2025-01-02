package com.coolismo.coins_service.infrastructure.shared;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

import com.coolismo.coins_service.infrastructure.repository.CoinsRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ScheduledTasksTest {

  @MockitoSpyBean private ScheduledTasks scheduledTasks;
  @MockitoSpyBean private CoinsRepository coinsRepository;

  @Before
  public void setUp() {
    coinsRepository.deleteAll().block();
    coinsRepository.count().log().block();
  }

  @Test
  void getCoinGeckoData_shouldReturnOk() {
    await()
        .atMost(10, SECONDS)
        .untilAsserted(() -> verify(scheduledTasks, atLeastOnce()).getCoinGeckoData());
  }
}
