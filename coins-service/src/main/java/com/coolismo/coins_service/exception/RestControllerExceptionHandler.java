package com.coolismo.coins_service.exception;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.server.MissingRequestValueException;
import reactor.core.publisher.Mono;

@RestControllerAdvice
public class RestControllerExceptionHandler extends ResponseEntityExceptionHandler {

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(NotFoundException.class)
  public @ResponseBody Mono<HttpErrorInfo> handleNotFoundException(
      NotFoundException e, ServerHttpRequest serverHttpRequest) {
    return createHttpErrorInfo(HttpStatus.NOT_FOUND, serverHttpRequest, e);
  }

  @ExceptionHandler(MissingRequestValueException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public @ResponseBody Mono<HttpErrorInfo> handleMissingRequestValueException(
      WebClientResponseException.BadRequest e, ServerHttpRequest serverHttpRequest) {
    return createHttpErrorInfo(HttpStatus.BAD_REQUEST, serverHttpRequest, e);
  }

  private Mono<HttpErrorInfo> createHttpErrorInfo(
      HttpStatus httpStatus, ServerHttpRequest serverHttpRequest, Exception e) {
    return Mono.just(
        HttpErrorInfo.builder()
            .httpStatus(httpStatus)
            .message(e.getMessage())
            .path(serverHttpRequest.getPath().pathWithinApplication().value())
            .localDateTime(LocalDateTime.now())
            .build());
  }
}
