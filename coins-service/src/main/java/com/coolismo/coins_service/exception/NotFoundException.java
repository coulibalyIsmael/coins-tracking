package com.coolismo.coins_service.exception;

public class NotFoundException extends Exception {
  private String message;

  public NotFoundException(String message) {
    this.message = message;
  }

  public NotFoundException(String message, Throwable cause) {
    super(message, cause);
  }

  public NotFoundException(Throwable cause) {
    super(cause);
  }
}
