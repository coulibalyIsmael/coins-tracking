package com.coolismo.coins_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@SpringBootApplication
@EnableFeignClients
@EnableR2dbcRepositories
public class CoinsServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(CoinsServiceApplication.class, args);
  }
}
