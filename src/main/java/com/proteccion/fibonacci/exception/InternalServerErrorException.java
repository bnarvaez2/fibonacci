package com.proteccion.fibonacci.exception;

public class InternalServerErrorException extends RuntimeException{
  public InternalServerErrorException(String message){
    super(message);
  }
}
