package com.coolismo.coins_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableR2dbcRepositories
@EnableScheduling
@ComponentScan(basePackages = {"com.coolismo.coins_service"})
public class CoinsServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(CoinsServiceApplication.class, args);
  }
}
