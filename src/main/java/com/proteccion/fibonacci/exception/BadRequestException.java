package com.proteccion.fibonacci.exception;

public class BadRequestException extends RuntimeException{
  public BadRequestException(String message){
    super(message);
  }
}
