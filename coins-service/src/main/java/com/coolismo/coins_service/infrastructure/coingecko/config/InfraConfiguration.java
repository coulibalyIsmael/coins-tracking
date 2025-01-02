package com.coolismo.coins_service.infrastructure.coingecko.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class InfraConfiguration {

  @Bean
  public WebClient webClient() {
    return WebClient.builder().build();
  }

  @Bean
  public ThreadPoolTaskScheduler taskScheduler() {
    ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
    taskScheduler.setPoolSize(3);
    taskScheduler.setThreadNamePrefix("coins-service-scheduler-");
    return taskScheduler;
  }
}
